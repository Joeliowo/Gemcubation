package com.zozo.gem.world.structures;

import com.zozo.gem.GemC;
import com.zozo.gem.init.GemBlocks;
import com.zozo.gem.world.structures.bases.Schematic;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SchematicLoader {

    public static Schematic loadSchematic(String schematic, String namee){
        NBTTagCompound schematicData;
        try {
            schematicData = CompressedStreamTools.readCompressed(SchematicLoader.class.getResourceAsStream(schematic));
        } catch (Exception e) {
            try {
                File file = new File(SchematicLoader.class.getResource(schematic).toExternalForm());
                schematicData = CompressedStreamTools.read(file);
            } catch (Exception e1) {
                return null;
            }
        }

        Map<BlockPos, IBlockState> structureBlocks = new HashMap<BlockPos, IBlockState>();

        boolean schematicaFormat = false;
        short length = schematicData.getShort("Length");
        short width = schematicData.getShort("Width");
        short height = schematicData.getShort("Height");
        byte[] blocks = schematicData.getByteArray("Blocks");
        byte[] additionalBlocks = null;
        HashMap<Short, String> additionalBlockNames = new HashMap<Short, String>();
        if (schematicData.hasKey("AddBlocks") && schematicData.hasKey("SchematicaMapping")) {
            schematicaFormat = true;
            byte[] addBlocks = schematicData.getByteArray("AddBlocks");
            additionalBlocks = new byte[addBlocks.length * 2];
            for (int i = 0; i < addBlocks.length; ++i) {
                additionalBlocks[i * 2] = (byte) (addBlocks[i] >> 4 & 0xF);
                additionalBlocks[i * 2 + 1] = (byte) (addBlocks[i] & 0xF);
            }

            NBTTagCompound blockNames = (NBTTagCompound) schematicData.getTag("SchematicaMapping");
            for (String name : blockNames.getKeySet()) {
                additionalBlockNames.put(blockNames.getShort(name), name);
            }
        }
        byte[] data = schematicData.getByteArray("Data");
        NBTTagList tileEntities = schematicData.getTagList("TileEntities", 10);
        NBTTagList entities = schematicData.getTagList("Entities", 10);

        // i = (y * Length + z) * Width + x
        for (short y = 0; y < height; ++y) {
            for (short z = 0; z < length; ++z) {
                for (short x = 0; x < width; ++x) {
                    try {
                        int index = (y * length + z) * width + x;
                        BlockPos pos = new BlockPos(x, y, z);
                        IBlockState blockState = null;
                        if (schematicaFormat && additionalBlocks != null) {
                            short blockID = (short) Byte.toUnsignedInt(blocks[index]);
                            blockID |= (additionalBlocks[index] & 0xFF) << 8;
                            blockState = Block.getBlockFromName(additionalBlockNames.get(blockID)).getStateFromMeta(Byte.toUnsignedInt(data[index]));
                        } else {
                            blockState = Block.getBlockById(Byte.toUnsignedInt(blocks[index])).getStateFromMeta(Byte.toUnsignedInt(data[index]));
                        }
                        structureBlocks.put(pos, blockState);
                    } catch (Exception e) {
                        structureBlocks.put(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
        return new Schematic(namee, width, height, length, structureBlocks, tileEntities, entities);
    }

    public static boolean spawnSchematic(Schematic structure, World world, BlockPos pos){
        Map<BlockPos, IBlockState> structureBlocks = structure.getStructureBlocks();
        for (BlockPos offset : structureBlocks.keySet()) {
            if (structureBlocks.get(offset).getBlock() == Blocks.AIR || structureBlocks.get(offset).getBlock() == Blocks.STRUCTURE_VOID) {
                continue;
            }
            world.setBlockState(pos.add(offset), structureBlocks.get(offset));
        }
        if(structure.name == "shrine"){
            BlockPos correction = new BlockPos(2, 5, 2);
            world.setBlockState(correction.add(pos), GemBlocks.PINK_ESSENCE.getDefaultState());
        }
        for (NBTBase nbt : structure.getTileEntities()) {
            TileEntity te = TileEntity.create(world, (NBTTagCompound) nbt);
            if (te != null) {
                int x = ((NBTTagCompound) nbt).getInteger("x");
                int y = ((NBTTagCompound) nbt).getInteger("y");
                int z = ((NBTTagCompound) nbt).getInteger("z");
                BlockPos tePos = new BlockPos(x,y,z);
                world.setTileEntity(pos.add(tePos), te);
            }
        }
        if (true) {
            for (NBTBase nbt : structure.getEntities()) {
                NBTTagList nbtPos = ((NBTTagCompound) nbt).getTagList("Pos", 6);
                double x = nbtPos.getDoubleAt(0);
                double y = nbtPos.getDoubleAt(1);
                double z = nbtPos.getDoubleAt(2);
                BlockPos ePos = new BlockPos(x, y, z);
                Entity e = EntityList.createEntityFromNBT((NBTTagCompound) nbt, world);
                if (e != null) {
                    e.setLocationAndAngles(pos.getX() + ePos.getX() + 0.5, pos.getY() + ePos.getY(), pos.getZ() + ePos.getZ() + 0.5, e.rotationYaw, e.rotationPitch);
                    world.spawnEntity(e);
                }
            }
        }
        return true;
    }
}
