// https://leetcode.com/problems/word-search-ii/

using System.Collections.Generic;
using System.Linq;
using System.Text;

public class WordDictionary : Dictionary<char, WordDictionary>
{
    public bool CanEnd = false;
}

public class Solution
{
    public IList<string> FindWords(char[,] board, string[] words)
    {
        var dict = new WordDictionary();
        foreach (var word in words)
        {
            var d = dict;
            for (var i = 0; i < word.Length; ++i)
            {
                WordDictionary d2;
                if (d.TryGetValue(word[i], out d2))
                {
                    d = d2;
                }
                else
                {
                    d[word[i]] = new WordDictionary();
                    d = d[word[i]];
                }
            }
            d.CanEnd = true;
        }

        var results = new List<string>();
        for (var i = 0; i < board.GetLength(0); ++i)
        {
            for (var j = 0; j < board.GetLength(1); ++j)
            {
                var sb = new StringBuilder();
                foreach (var word in FindWords_Search(board, i, j, sb, dict))
                {
                    results.Add(word);
                }
            }
        }
        // TODO: remove duplicates
        return results.Distinct().ToList();
    }

    private readonly int[,] FindWords_SearchPath = new int[4, 2] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private IEnumerable<string> FindWords_Search(char[,] board, int i, int j, StringBuilder sb, WordDictionary dict)
    {
        WordDictionary childDict;
        if (dict.Keys.Count > 0 && char.IsLower(board[i, j]) && dict.TryGetValue(board[i, j], out childDict))
        {
            sb.Append(board[i, j]);
            if (childDict.CanEnd)
            {
                yield return sb.ToString();
            }

            board[i, j] = char.ToUpper(board[i, j]);
            for (var k = 0; k < 4; ++k)
            {
                var newI = i + FindWords_SearchPath[k, 0];
                var newJ = j + FindWords_SearchPath[k, 1];
                if (newI >= 0 && newI < board.GetLength(0) && newJ >= 0 && newJ < board.GetLength(1))
                {
                    foreach (var word in FindWords_Search(board, newI, newJ, sb, childDict))
                    {
                        yield return word;
                    }
                }
            }
            sb.Remove(sb.Length - 1, 1);
            board[i, j] = char.ToLower(board[i, j]);
        }
    }
}