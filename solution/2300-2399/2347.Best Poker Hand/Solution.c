char* bestHand(int* ranks, int ranksSize, char* suits, int suitsSize) {
    bool isFlush = true;
    for (int i = 1; i < suitsSize; i++) {
        if (suits[0] != suits[i]) {
            isFlush = false;
            break;
        }
    }
    if (isFlush) {
        return "Flush";
    }
    int count[14] = {0};
    bool isPair = false;
    for (int i = 0; i < ranksSize; i++) {
        if (++count[ranks[i]] == 3) {
            return "Three of a Kind";
        }
        isPair = isPair || count[ranks[i]] == 2;
    }
    if (isPair) {
        return "Pair";
    }
    return "High Card";
}
