#define max(a, b) (((a) > (b)) ? (a) : (b))

int minOperations(char** logs, int logsSize) {
    int depth = 0;
    for (int i = 0; i < logsSize; i++) {
        char* log = logs[i];
        if (!strcmp(log, "../")) {
            depth = max(0, depth - 1);
        } else if (strcmp(log, "./")) {
            depth++;
        }
    }
    return depth;
}
