package jeresources.util;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.DimensionType;
import net.minecraft.world.GameType;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class FakeClientWorld extends World {
	public static final WorldSettings worldSettings = new WorldSettings(0, GameType.SURVIVAL, true, false, WorldType.DEFAULT);
	public static final WorldInfo worldInfo = new WorldInfo(worldSettings, "just_enough_resources_fake");
	public static final FakeSaveHandler saveHandler = new FakeSaveHandler();
	public static final WorldProvider worldProvider = new WorldProvider() {
		@Override
		public DimensionType getDimensionType() {
			return DimensionType.OVERWORLD;
		}
	};

	public FakeClientWorld() {
		super(saveHandler, worldInfo, worldProvider, new Profiler(), true);
	}

	@Override
	protected IChunkProvider createChunkProvider() {
		return new IChunkProvider() {
			@Nullable
			@Override
			public Chunk getLoadedChunk(int x, int z) {
				return null;
			}

			@Override
			public Chunk provideChunk(int x, int z) {
				return null;
			}

			@Override
			public boolean unloadQueuedChunks() {
				return false;
			}

			@Override
			public String makeString() {
				return null;
			}
		};
	}

	@Override
	protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
		return false;
	}

	private static class FakeSaveHandler implements ISaveHandler {

		@Override
		public WorldInfo loadWorldInfo() {
			return worldInfo;
		}

		@Override
		public void checkSessionLock() throws MinecraftException {

		}

		@Override
		public IChunkLoader getChunkLoader(WorldProvider provider) {
			return new IChunkLoader() {
				@Nullable
				@Override
				public Chunk loadChunk(World worldIn, int x, int z) throws IOException {
					return null;
				}

				@Override
				public void saveChunk(World worldIn, Chunk chunkIn) throws MinecraftException, IOException {

				}

				@Override
				public void saveExtraChunkData(World worldIn, Chunk chunkIn) throws IOException {

				}

				@Override
				public void chunkTick() {

				}

				@Override
				public void saveExtraData() {

				}
			};
		}

		@Override
		public void saveWorldInfoWithPlayer(WorldInfo worldInformation, NBTTagCompound tagCompound) {

		}

		@Override
		public void saveWorldInfo(WorldInfo worldInformation) {

		}

		@Override
		public IPlayerFileData getPlayerNBTManager() {
			return new IPlayerFileData() {
				@Override
				public void writePlayerData(EntityPlayer player) {

				}

				@Override
				public NBTTagCompound readPlayerData(EntityPlayer player) {
					return new NBTTagCompound();
				}

				@Override
				public String[] getAvailablePlayerDat() {
					return new String[0];
				}
			};
		}

		@Override
		public void flush() {

		}

		@Override
		public File getWorldDirectory() {
			return null;
		}

		@Override
		public File getMapFileFromName(String mapName) {
			return null;
		}

		@Override
		public TemplateManager getStructureTemplateManager() {
			return null;
		}
	}
}
