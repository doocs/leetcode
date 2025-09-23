class Logger {
public:
    Logger() {
    }

    bool shouldPrintMessage(int timestamp, string message) {
        if (ts.contains(message) && ts[message] > timestamp) {
            return false;
        }
        ts[message] = timestamp + 10;
        return true;
    }

private:
    unordered_map<string, int> ts;
};

/**
 * Your Logger object will be instantiated and called as such:
 * Logger* obj = new Logger();
 * bool param_1 = obj->shouldPrintMessage(timestamp,message);
 */
