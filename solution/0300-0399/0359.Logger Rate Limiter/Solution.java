class Logger {
    private Map<String, Integer> ts = new HashMap<>();

    public Logger() {
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        int t = ts.getOrDefault(message, 0);
        if (timestamp < t) {
            return false;
        }
        ts.put(message, timestamp + 10);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
