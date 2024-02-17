package the.grid.smp.desertum.db;

import the.grid.smp.desertum.Desertum;
import the.grid.smp.desertum.data.ChunkPos;
import the.grid.smp.desertum.util.SQLite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChunkDatabase {

    private static final String TABLE = "chunk_updates";
    private final SQLite db;

    public ChunkDatabase(Desertum desertum) {
        this.db = SQLite.open(desertum.getLogger(), desertum.getDataFolder());

        if (this.db == null)
            return;

        this.db.update("CREATE TABLE IF NOT EXISTS %s (x INTEGER, z INTEGER, updated BIGINT)", TABLE);
    }

    public Collection<ChunkPos> getChunks(long max) {
        List<ChunkPos> pos = new ArrayList<>();

        this.db.query(result -> {
            while (result.next()) {
                pos.add(new ChunkPos(
                        result.getInt("x"),
                        result.getInt("z")
                ));
            }
        }, "SELECT * FROM %s WHERE updated <= %d", TABLE, max);

        return pos;
    }

    public void setChunk(ChunkPos pos, long updated) {
        this.setChunk(pos.x(), pos.z(), updated);
    }

    public void setChunk(int x, int z, long updated) {
        this.db.update("INSERT INTO %s (x, z, updated) values (%d, %d, %d) " +
                        "ON DUPLICATE KEY UPDATE x = %d, z = %d, updated = %d",
                TABLE, x, z, updated, x, z, updated
        );
    }

    public void remove(ChunkPos pos) {
        this.remove(pos.x(), pos.z());
    }

    public void remove(int x, int z) {
        this.db.update("DELETE FROM %s WHERE x = %d AND z = %d", TABLE, x, z);
    }

    public void close() {
        this.db.close();
    }
}
