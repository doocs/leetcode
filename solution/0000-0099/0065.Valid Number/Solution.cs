// https://leetcode.com/problems/valid-number/

using System.Text.RegularExpressions;

public partial class Solution
{
    private readonly Regex _isNumber_Regex = new Regex(@"^\s*[+-]?(\d+(\.\d*)?|\.\d+)([Ee][+-]?\d+)?\s*$");
    public bool IsNumber(string s)
    {
        return _isNumber_Regex.IsMatch(s);
    }
}