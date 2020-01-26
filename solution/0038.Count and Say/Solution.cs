using System.Text;
public class Solution {
    public string CountAndSay(int n) {
        var s = "1";
        while (n > 1)
        {
            var sb = new StringBuilder();
            var lastChar = '1';
            var count = 0;
            foreach (var ch in s)
            {
                if (count > 0 && lastChar == ch)
                {
                    ++count;
                }
                else
                {
                    if (count > 0)
                    {
                        sb.Append(count);
                        sb.Append(lastChar);
                    }
                    lastChar = ch;
                    count = 1;
                }
            }
            if (count > 0)
            {
                sb.Append(count);
                sb.Append(lastChar);
            }
            s = sb.ToString();
            --n;
        }
        return s;
    }
}