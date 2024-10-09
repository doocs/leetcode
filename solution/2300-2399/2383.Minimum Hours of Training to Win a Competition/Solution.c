int minNumberOfHours(int x, int y, int* energy, int energySize, int* experience, int experienceSize) {
    int ans = 0;
    for (int i = 0; i < energySize; ++i) {
        int dx = energy[i], dy = experience[i];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
