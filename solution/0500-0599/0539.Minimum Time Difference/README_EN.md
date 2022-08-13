# [539. Minimum Time Difference](https://leetcode.com/problems/minimum-time-difference)

[中文文档](/solution/0500-0599/0539.Minimum%20Time%20Difference/README.md)

## Description

Given a list of 24-hour clock time points in <strong>&quot;HH:MM&quot;</strong> format, return <em>the minimum <b>minutes</b> difference between any two time-points in the list</em>.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> timePoints = ["23:59","00:00"]
<strong>Output:</strong> 1
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> timePoints = ["00:00","23:59","00:00"]
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= timePoints.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>timePoints[i]</code> is in the format <strong>&quot;HH:MM&quot;</strong>.</li>
</ul>

## Solutions

1. Convert all the times into minutes values `mins`, i.e. `13:14` => `13 * 60 + 14`.
2. Sort the `mins`.
3. Push the time `mins[0] + 24 * 60` to deal with min and max diff.
4. For each value in `mins[1:]`, calculate the min diff `mins[i] - mins[i - 1]`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:
        if len(timePoints) > 24 * 60:
            return 0
        mins = sorted(int(t[:2]) * 60 + int(t[3:]) for t in timePoints)
        mins.append(mins[0] + 24 * 60)
        res = mins[-1]
        for i in range(1, len(mins)):
            res = min(res, mins[i] - mins[i - 1])
        return res
```

### **Java**

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        List<Integer> mins = new ArrayList<>();
        for (String t : timePoints) {
            String[] time = t.split(":");
            mins.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }
        Collections.sort(mins);
        mins.add(mins.get(0) + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < mins.size(); ++i) {
            res = Math.min(res, mins.get(i) - mins.get(i - 1));
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 24 * 60)
            return 0;
        vector<int> mins;
        for (auto t : timePoints)
            mins.push_back(stoi(t.substr(0, 2)) * 60 + stoi(t.substr(3)));
        sort(mins.begin(), mins.end());
        mins.push_back(mins[0] + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < mins.size(); ++i)
            res = min(res, mins[i] - mins[i - 1]);
        return res;
    }
};
```

### **Go**

```go
func findMinDifference(timePoints []string) int {
	if len(timePoints) > 24*60 {
		return 0
	}
	var mins []int
	for _, t := range timePoints {
		time := strings.Split(t, ":")
		h, _ := strconv.Atoi(time[0])
		m, _ := strconv.Atoi(time[1])
		mins = append(mins, h*60+m)
	}
	sort.Ints(mins)
	mins = append(mins, mins[0]+24*60)
	res := 24 * 60
	for i := 1; i < len(mins); i++ {
		res = min(res, mins[i]-mins[i-1])
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function findMinDifference(timePoints: string[]): number {
    const mins = timePoints
        .map(item => Number(item.slice(0, 2)) * 60 + Number(item.slice(3, 5)))
        .sort((a, b) => a - b);
    const n = mins.length;
    let res = Infinity;
    for (let i = 0; i < n - 1; i++) {
        res = Math.min(res, mins[i + 1] - mins[i]);
    }

    const first = mins[0] + 24 * 60;
    const last = mins[n - 1];
    res = Math.min(res, first - last);

    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
