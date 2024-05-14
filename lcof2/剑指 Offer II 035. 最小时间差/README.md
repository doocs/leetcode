---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20035.%20%E6%9C%80%E5%B0%8F%E6%97%B6%E9%97%B4%E5%B7%AE/README.md
---

# [剑指 Offer II 035. 最小时间差](https://leetcode.cn/problems/569nqc)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 24 小时制（小时:分钟 <strong>&quot;HH:MM&quot;</strong>）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;00:00&quot;,&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= timePoints &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>timePoints[i]</code> 格式为 <strong>&quot;HH:MM&quot;</strong></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 539&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/minimum-time-difference/">https://leetcode.cn/problems/minimum-time-difference/</a></p>

## 解法

### 方法一：排序

我们注意到，时间点最多只有 $24 \times 60$ 个，因此，当 $timePoints$ 长度超过 $24 \times 60$，说明有重复的时间点，提前返回 $0$。

接下来，我们首先遍历时间列表，将其转换为“分钟制”列表 $mins$，比如，对于时间点 `13:14`，将其转换为 $13 \times 60 + 14$。

接着将“分钟制”列表按升序排列，然后将此列表的最小时间 $mins[0]$ 加上 $24 \times 60$ 追加至列表尾部，用于处理最大值、最小值的差值这种特殊情况。

最后遍历“分钟制”列表，找出相邻两个时间的最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为时间点个数。

<!-- tabs:start -->

```python
class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:
        if len(timePoints) > 24 * 60:
            return 0
        mins = sorted(int(t[:2]) * 60 + int(t[3:]) for t in timePoints)
        mins.append(mins[0] + 24 * 60)
        return min(b - a for a, b in pairwise(mins))
```

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
        int ans = 1 << 30;
        for (int i = 1; i < mins.size(); ++i) {
            ans = Math.min(ans, mins.get(i) - mins.get(i - 1));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        vector<int> mins;
        for (auto& t : timePoints) {
            mins.push_back(stoi(t.substr(0, 2)) * 60 + stoi(t.substr(3)));
        }
        sort(mins.begin(), mins.end());
        mins.push_back(mins[0] + 24 * 60);
        int ans = 1 << 30;
        for (int i = 1; i < mins.size(); ++i) {
            ans = min(ans, mins[i] - mins[i - 1]);
        }
        return ans;
    }
};
```

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
	ans := 1 << 30
	for i, x := range mins[1:] {
		ans = min(ans, x-mins[i])
	}
	return ans
}
```

```ts
function findMinDifference(timePoints: string[]): number {
    if (timePoints.length > 24 * 60) {
        return 0;
    }
    const mins: number[] = timePoints.map(timePoint => {
        const [hour, minute] = timePoint.split(':').map(num => parseInt(num));
        return hour * 60 + minute;
    });
    mins.sort((a, b) => a - b);
    mins.push(mins[0] + 24 * 60);
    let ans = 1 << 30;
    for (let i = 1; i < mins.length; ++i) {
        ans = Math.min(ans, mins[i] - mins[i - 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
