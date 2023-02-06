class LogSystem {
    private List<Log> logs = new ArrayList<>();
    private Map<String, Integer> d = new HashMap<>();

    public LogSystem() {
        d.put("Year", 4);
        d.put("Month", 7);
        d.put("Day", 10);
        d.put("Hour", 13);
        d.put("Minute", 16);
        d.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        logs.add(new Log(id, timestamp));
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> ans = new ArrayList<>();
        int i = d.get(granularity);
        String s = start.substring(0, i);
        String e = end.substring(0, i);
        for (var log : logs) {
            String t = log.ts.substring(0, i);
            if (s.compareTo(t) <= 0 && t.compareTo(e) <= 0) {
                ans.add(log.id);
            }
        }
        return ans;
    }
}

class Log {
    int id;
    String ts;

    Log(int id, String ts) {
        this.id = id;
        this.ts = ts;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(start,end,granularity);
 */