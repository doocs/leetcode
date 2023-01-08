class Solution {
public:
    string categorizeBox(int length, int width, int height, int mass) {
        long v = (long) length * width * height;
        int bulky = length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 ? 1 : 0;
        int heavy = mass >= 100 ? 1 : 0;
        string d[4] = {"Neither", "Bulky", "Heavy", "Both"};
        int i = heavy << 1 | bulky;
        return d[i];
    }
};