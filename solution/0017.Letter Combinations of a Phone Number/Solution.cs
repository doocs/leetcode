using System.Collections.Generic;
using System.Linq;

public class Solution {
    private static string[] chars = {
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };

    public IList<string> LetterCombinations(string digits) {
        var numbers = digits.Where(d => d >= '2' && d <= '9').Select(d => d - '2').ToArray();
        var states = new int[numbers.Length];
        var results = new List<string>();
        if (numbers.Length == 0) return results;
        while (true) {
            results.Add(new string(states.Select((s, j) => chars[numbers[j]][s]).ToArray()));
            var i = states.Length - 1;
            ++states[i];
            while (i >= 0 && states[i] == chars[numbers[i]].Length)
            {
                states[i] = 0;
                --i;
                if (i >= 0)
                {
                    ++states[i];
                }
            }
            if (i < 0) return results;
        }
    }
}