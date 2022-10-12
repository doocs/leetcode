# [248. Strobogrammatic Number III](https://leetcode.com/problems/strobogrammatic-number-iii)

[中文文档](/solution/0200-0299/0248.Strobogrammatic%20Number%20III/README.md)

## Description

<p>Given two strings low and high that represent two integers <code>low</code> and <code>high</code> where <code>low &lt;= high</code>, return <em>the number of <strong>strobogrammatic numbers</strong> in the range</em> <code>[low, high]</code>.</p>

<p>A <strong>strobogrammatic number</strong> is a number that looks the same when rotated <code>180</code> degrees (looked at upside down).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> low = "50", high = "100"
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> low = "0", high = "0"
<strong>Output:</strong> 1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= low.length, high.length &lt;= 15</code></li>
	<li><code>low</code> and <code>high</code> consist of only digits.</li>
	<li><code>low &lt;= high</code></li>
	<li><code>low</code> and <code>high</code> do not contain any leading zeros except for zero itself.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def strobogrammaticInRange(self, low: str, high: str) -> int:
        def dfs(u):
            if u == 0:
                return ['']
            if u == 1:
                return ['0', '1', '8']
            ans = []
            for v in dfs(u - 2):
                for l, r in ('11', '88', '69', '96'):
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        a, b = len(low), len(high)
        low, high = int(low), int(high)
        ans = 0
        for n in range(a, b + 1):
            for s in dfs(n):
                if low <= int(s) <= high:
                    ans += 1
        return ans
```

### **Java**

```java
class Solution {
    private static final int[][] PAIRS = {{1, 1}, {8, 8}, {6, 9}, {9, 6}};
    private int n;

    public int strobogrammaticInRange(String low, String high) {
        int a = low.length(), b = high.length();
        long l = Long.parseLong(low), r = Long.parseLong(high);
        int ans = 0;
        for (n = a; n <= b; ++n) {
            for (String s : dfs(n)) {
                long v = Long.parseLong(s);
                if (l <= v && v <= r) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private List<String> dfs(int u) {
        if (u == 0) {
            return Collections.singletonList("");
        }
        if (u == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        for (String v : dfs(u - 2)) {
            for (var p : PAIRS) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add(0 + v + 0);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    const vector<pair<char, char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

    int strobogrammaticInRange(string low, string high) {
        int n;
        function<vector<string>(int)> dfs = [&](int u) {
            if (u == 0) return vector<string>{""};
            if (u == 1) return vector<string>{"0", "1", "8"};
            vector<string> ans;
            for (auto& v : dfs(u - 2)) {
                for (auto& [l, r] : pairs) ans.push_back(l + v + r);
                if (u != n) ans.push_back('0' + v + '0');
            }
            return ans;
        };

        int a = low.size(), b = high.size();
        int ans = 0;
        ll l = stoll(low), r = stoll(high);
        for (n = a; n <= b; ++n) {
            for (auto& s : dfs(n)) {
                ll v = stoll(s);
                if (l <= v && v <= r) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func strobogrammaticInRange(low string, high string) int {
	n := 0
	var dfs func(int) []string
	dfs = func(u int) []string {
		if u == 0 {
			return []string{""}
		}
		if u == 1 {
			return []string{"0", "1", "8"}
		}
		var ans []string
		pairs := [][]string{{"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"}}
		for _, v := range dfs(u - 2) {
			for _, p := range pairs {
				ans = append(ans, p[0]+v+p[1])
			}
			if u != n {
				ans = append(ans, "0"+v+"0")
			}
		}
		return ans
	}
	a, b := len(low), len(high)
	l, _ := strconv.Atoi(low)
	r, _ := strconv.Atoi(high)
	ans := 0
	for n = a; n <= b; n++ {
		for _, s := range dfs(n) {
			v, _ := strconv.Atoi(s)
			if l <= v && v <= r {
				ans++
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
