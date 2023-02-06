int countPoints(char* rings) {
    int target = (1 << ('R' - 'A')) + (1 << ('G' - 'A')) + (1 << ('B' - 'A'));
    int count[10] = {0};
    for (int i = 0; rings[i]; i += 2) {
        count[rings[i + 1] - '0'] |= 1 << (rings[i] - 'A');
    }
    int ans = 0;
    for (int i = 0; i < 10; i++) {
        if (count[i] == target) {
            ans++;
        }
    }
    return ans;
}
