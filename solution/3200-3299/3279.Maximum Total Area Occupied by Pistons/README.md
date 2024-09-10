---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3279.Maximum%20Total%20Area%20Occupied%20by%20Pistons/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
    - 计数
    - 前缀和
    - 模拟
---

<!-- problem:start -->

# [3279. Maximum Total Area Occupied by Pistons 🔒](https://leetcode.cn/problems/maximum-total-area-occupied-by-pistons)

[English Version](/solution/3200-3299/3279.Maximum%20Total%20Area%20Occupied%20by%20Pistons/README_EN.md)

## 题目描述

<!-- description:start -->

<p>There are several pistons in an old car engine, and we want to calculate the <strong>maximum</strong> possible area <strong>under</strong> the pistons.</p>

<p>You are given:</p>

<ul>
	<li>An integer <code>height</code>, representing the <strong>maximum</strong> height a piston can reach.</li>
	<li>An integer array <code>positions</code>, where <code>positions[i]</code> is the current position of piston <code>i</code>, which is equal to the current area <strong>under</strong> it.</li>
	<li>A string <code>directions</code>, where <code>directions[i]</code> is the current moving direction of piston <code>i</code>, <code>&#39;U&#39;</code> for up, and <code>&#39;D&#39;</code> for down.</li>
</ul>

<p>Each second:</p>

<ul>
	<li>Every piston moves in its current direction 1 unit. e.g., if the direction is up, <code>positions[i]</code> is incremented by 1.</li>
	<li>If a piston has reached one of the ends, i.e., <code>positions[i] == 0</code> or <code>positions[i] == height</code>, its direction will change.</li>
</ul>

<p>Return the <em>maximum possible area</em> under all the pistons.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">height = 5, positions = [2,5], directions = &quot;UD&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The current position of the pistons has the maximum possible area under it.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">height = 6, positions = [0,0,6,3], directions = &quot;UUDU&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>After 3 seconds, the pistons will be in positions <code>[3, 3, 3, 6]</code>, which has the maximum possible area under it.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= positions.length == directions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= positions[i] &lt;= height</code></li>
	<li><code>directions[i]</code> is either <code>&#39;U&#39;</code> or <code>&#39;D&#39;</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, height: int, positions: List[int], directions: str) -> int:
        delta = defaultdict(int)
        diff = res = 0
        for pos, dir in zip(positions, directions):
            res += pos
            if dir == "U":
                diff += 1
                delta[height - pos] -= 2
                delta[height * 2 - pos] += 2
            else:
                diff -= 1
                delta[pos] += 2
                delta[height + pos] -= 2
        ans = res
        pre = 0
        for cur, d in sorted(delta.items()):
            res += (cur - pre) * diff
            pre = cur
            diff += d
            ans = max(ans, res)
        return ans
```

#### Java

```java
class Solution {
    public long maxArea(int height, int[] positions, String directions) {
        Map<Integer, Integer> delta = new TreeMap<>();
        int diff = 0;
        long res = 0;
        for (int i = 0; i < positions.length; ++i) {
            int pos = positions[i];
            char dir = directions.charAt(i);
            res += pos;
            if (dir == 'U') {
                ++diff;
                delta.merge(height - pos, -2, Integer::sum);
                delta.merge(height * 2 - pos, 2, Integer::sum);
            } else {
                --diff;
                delta.merge(pos, 2, Integer::sum);
                delta.merge(height + pos, -2, Integer::sum);
            }
        }
        long ans = res;
        int pre = 0;
        for (var e : delta.entrySet()) {
            int cur = e.getKey();
            int d = e.getValue();
            res += (long) (cur - pre) * diff;
            pre = cur;
            diff += d;
            ans = Math.max(ans, res);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxArea(int height, vector<int>& positions, string directions) {
        map<int, int> delta;
        int diff = 0;
        long long res = 0;

        for (int i = 0; i < positions.size(); ++i) {
            int pos = positions[i];
            char dir = directions[i];
            res += pos;

            if (dir == 'U') {
                ++diff;
                delta[height - pos] -= 2;
                delta[height * 2 - pos] += 2;
            } else {
                --diff;
                delta[pos] += 2;
                delta[height + pos] -= 2;
            }
        }

        long long ans = res;
        int pre = 0;

        for (const auto& [cur, d] : delta) {
            res += static_cast<long long>(cur - pre) * diff;
            pre = cur;
            diff += d;
            ans = max(ans, res);
        }

        return ans;
    }
};
```

#### Go

```go
func maxArea(height int, positions []int, directions string) int64 {
	delta := make(map[int]int)
	diff := 0
	var res int64 = 0
	for i, pos := range positions {
		dir := directions[i]
		res += int64(pos)

		if dir == 'U' {
			diff++
			delta[height-pos] -= 2
			delta[height*2-pos] += 2
		} else {
			diff--
			delta[pos] += 2
			delta[height+pos] -= 2
		}
	}
	ans := res
	pre := 0
	keys := make([]int, 0, len(delta))
	for key := range delta {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, cur := range keys {
		d := delta[cur]
		res += int64(cur-pre) * int64(diff)
		pre = cur
		diff += d
		ans = max(ans, res)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
