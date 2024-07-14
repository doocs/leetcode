---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20035.%20%E6%9C%80%E5%B0%8F%E6%97%B6%E9%97%B4%E5%B7%AE/README.md
---

<!-- problem:start -->

# [剑指 Offer II 035. 最小时间差](https://leetcode.cn/problems/569nqc)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们注意到，时间点最多只有 $24 \times 60 = 1440$ 个，因此，当 $timePoints$ 长度超过 $1440$，说明有重复的时间点，提前返回 $0$。

接下来，我们首先遍历时间列表，将其转换为“分钟制”列表 $nums$，比如，对于时间点 `13:14`，将其转换为 $13 \times 60 + 14$。

接着将“分钟制”列表按升序排列，然后将此列表的最小时间 $nums[0]$ 加上 $1440$ 追加至列表尾部，用于处理最大值、最小值的差值这种特殊情况。

最后遍历“分钟制”列表，找出相邻两个时间的最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为时间点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:
        if len(timePoints) > 1440:
            return 0
        nums = sorted(int(x[:2]) * 60 + int(x[3:]) for x in timePoints)
        nums.append(nums[0] + 1440)
        return min(b - a for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int n = timePoints.size();
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            String[] t = timePoints.get(i).split(":");
            nums[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
        Arrays.sort(nums, 0, n);
        nums[n] = nums[0] + 1440;
        int ans = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int n = timePoints.size();
        vector<int> nums(n + 1);
        for (int i = 0; i < n; ++i) {
            int hours = stoi(timePoints[i].substr(0, 2));
            int minutes = stoi(timePoints[i].substr(3, 2));
            nums[i] = hours * 60 + minutes;
        }
        sort(nums.begin(), nums.begin() + n);
        nums[n] = nums[0] + 1440;
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            ans = min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func findMinDifference(timePoints []string) int {
	if len(timePoints) > 1440 {
		return 0
	}

	n := len(timePoints)
	nums := make([]int, n+1)
	for i, time := range timePoints {
		parts := strings.Split(time, ":")
		hours, _ := strconv.Atoi(parts[0])
		minutes, _ := strconv.Atoi(parts[1])
		nums[i] = hours*60 + minutes
	}

	sort.Ints(nums[:n])
	nums[n] = nums[0] + 1440

	ans := 1 << 30
	for i := 1; i <= n; i++ {
		ans = min(ans, nums[i]-nums[i-1])
	}

	return ans
}
```

#### TypeScript

```ts
function findMinDifference(timePoints: string[]): number {
    if (timePoints.length > 1440) {
        return 0;
    }
    const n = timePoints.length;
    const nums: number[] = Array(n + 1);
    for (let i = 0; i < n; ++i) {
        const [hours, minutes] = timePoints[i].split(':').map(Number);
        nums[i] = hours * 60 + minutes;
    }
    nums.sort((a, b) => a - b);
    nums[n] = nums[0] + 1440;
    let ans = 1 << 30;
    for (let i = 1; i <= n; ++i) {
        ans = Math.min(ans, nums[i] - nums[i - 1]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min_difference(time_points: Vec<String>) -> i32 {
        if time_points.len() > 1440 {
            return 0;
        }

        let n = time_points.len();
        let mut nums: Vec<i32> = Vec::with_capacity(n + 1);

        for time in time_points.iter() {
            let parts: Vec<i32> = time.split(':').map(|s| s.parse().unwrap()).collect();
            let minutes = parts[0] * 60 + parts[1];
            nums.push(minutes);
        }

        nums.sort();
        nums.push(nums[0] + 1440);

        let mut ans = i32::MAX;
        for i in 1..=n {
            ans = ans.min(nums[i] - nums[i - 1]);
        }

        ans
    }
}
```

#### Swift

```swift
class Solution {
    func findMinDifference(_ timePoints: [String]) -> Int {
        if timePoints.count > 1440 {
            return 0
        }

        var nums = [Int]()

        for t in timePoints {
            let time = t.split(separator: ":").map { Int($0)! }
            nums.append(time[0] * 60 + time[1])
        }

        nums.sort()
        nums.append(nums[0] + 1440)

        var ans = Int.max
        for i in 1..<nums.count {
            ans = min(ans, nums[i] - nums[i - 1])
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
