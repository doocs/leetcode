class Solution {
public:
    string reformatDate(string date) {
        string months = " JanFebMarAprMayJunJulAugSepOctNovDec";
        stringstream ss(date);
        string year, month, t;
        int day;
        ss >> day >> t >> month >> year;
        month = to_string(months.find(month) / 3 + 1);
        return year + "-" + (month.size() == 1 ? "0" + month : month) + "-" + (day > 9 ? "" : "0") + to_string(day);
    }
};