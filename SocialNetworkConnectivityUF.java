public class SocialNetworkConnectivity {
    private int[] id;
    private int[] sz;
    private int components;

    public SocialNetworkConnectivity(int n) {
        id = new int[n];
        sz = new int[n];
        components = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // Path compression
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        components--;
    }

    public String earliestTime(LogEntry[] logEntries) {
        String earliestTimestamp = null;
        for (LogEntry entry : logEntries) {
            int p = entry.member1;
            int q = entry.member2;
            union(p, q);
            if (components == 1) {
                earliestTimestamp = entry.timestamp;
                break;
            }
        }
        return earliestTimestamp;
    }

    // LogEntry class to represent each log entry
    public static class LogEntry {
        String timestamp;
        int member1;
        int member2;

        public LogEntry(String timestamp, int member1, int member2) {
            this.timestamp = timestamp;
            this.member1 = member1;
            this.member2 = member2;
        }
    }
}

