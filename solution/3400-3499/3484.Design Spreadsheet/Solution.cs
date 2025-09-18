public class Spreadsheet {
    private Dictionary<string, int> d = new Dictionary<string, int>();

    public Spreadsheet(int rows) {
    }

    public void SetCell(string cell, int value) {
        d[cell] = value;
    }

    public void ResetCell(string cell) {
        d.Remove(cell);
    }

    public int GetValue(string formula) {
        int ans = 0;
        foreach (string cell in formula.Substring(1).Split('+')) {
            ans += char.IsDigit(cell[0]) ? int.Parse(cell)
                                         : (d.ContainsKey(cell) ? d[cell] : 0);
        }
        return ans;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.SetCell(cell,value);
 * obj.ResetCell(cell);
 * int param_3 = obj.GetValue(formula);
 */
