#define min(a, b) (((a) < (b)) ? (a) : (b))

int hardestWorker(int n, int** logs, int logsSize, int* logsColSize) {
    int res = 0;
    int max = 0;
    int pre = 0;
    for (int i = 0; i < logsSize; i++) {
        int t = logs[i][1] - pre;
        if (t > max || (t == max && res > logs[i][0])) {
            res = logs[i][0];
            max = t;
        }
        pre = logs[i][1];
    }
    return res;
}
