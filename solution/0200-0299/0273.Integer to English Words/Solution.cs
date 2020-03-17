using System.Collections.Generic;
using System.Linq;

public class Solution {
    private string[] bases = { "Thousand", "Million", "Billion" };
    public string NumberToWords(int num) {
        if (num == 0)
        {
            return "Zero";
        }
        var baseIndex = -1;
        var parts = new List<string>();
        while (num > 0)
        {
            var part = NumberToWordsInternal(num % 1000);
            if (part.Length > 0 && baseIndex >= 0)
            {
                part = JoinParts(part, bases[baseIndex]);
            }
            parts.Add(part);
            baseIndex++;
            num /= 1000;
        }
        parts.Reverse();
        return JoinParts(parts);
    }

    private string JoinParts(IEnumerable<string> parts)
    {
        return string.Join(" ", parts.Where(p => p.Length > 0));
    }

    private string JoinParts(params string[] parts)
    {
        return JoinParts((IEnumerable<string>)parts);
    }

    private string NumberToWordsInternal(int num)
    {
        switch(num)
        {
            case 0: return "";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }

        if (num < 100)
        {
            string part1;
            switch (num/10)
            {
                case 2: part1 = "Twenty"; break;
                case 3: part1 = "Thirty"; break;
                case 4: part1 = "Forty"; break;
                case 5: part1 = "Fifty"; break;
                case 6: part1 = "Sixty"; break;
                case 7: part1 = "Seventy"; break;
                case 8: part1 = "Eighty"; break;
                case 9: default: part1 = "Ninety"; break;
            }
            var part2 = NumberToWordsInternal(num % 10);
            return JoinParts(part1, part2);
        }

        {
            var part1 = NumberToWordsInternal(num / 100);
            var part2 = NumberToWordsInternal(num % 100);
            return JoinParts(part1, "Hundred", part2);
        }
    }
}