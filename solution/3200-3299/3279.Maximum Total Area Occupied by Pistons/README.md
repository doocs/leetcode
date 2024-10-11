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

# [3279. 活塞占据的最大总区域 🔒](https://leetcode.cn/problems/maximum-total-area-occupied-by-pistons)

[English Version](/solution/3200-3299/3279.Maximum%20Total%20Area%20Occupied%20by%20Pistons/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一台旧车的引擎中有一些活塞，我们想要计算活塞 <strong>下方</strong> 的 <strong>最大</strong> 区域。</p>

<p>给定：</p>

<ul>
	<li>一个整数&nbsp;<code>height</code>，表示活塞 <strong>最大</strong> 可到达的高度。</li>
	<li>一个整数数组&nbsp;<code>positions</code>，其中&nbsp;<code>positions[i]</code>&nbsp;是活塞&nbsp;<code>i</code>&nbsp;的当前位置，等于其 <strong>下方</strong>&nbsp;的当前区域。</li>
	<li>一个字符串&nbsp;<code>directions</code>，其中&nbsp;<code>directions[i]</code>&nbsp;是活塞&nbsp;<code>i</code>&nbsp;的当前移动方向，<code>'U'</code> 表示向上，<code>'D'</code> 表示向下。</li>
</ul>

<p>每一秒：</p>

<ul>
	<li>每个活塞向它的当前方向移动 1 单位。即如果方向向上，<code>positions[i]</code> 增加 1。</li>
	<li>如果一个活塞到达了其中一个终点，即&nbsp;<code>positions[i] == 0</code> 或&nbsp;<code>positions[i] == height</code>，它的方向将会改变。</li>
</ul>

<p>返回所有活塞下方的最大可能区域。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">height = 5, positions = [2,5], directions = "UD"</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><strong>解释：</strong></p>

<p>当前活塞的位置下方区域最大。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">height = 6, positions = [0,0,6,3], directions = "UUDU"</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<p>三秒后，活塞将会位于&nbsp;<code>[3, 3, 3, 6]</code>，此时下方区域最大。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= positions.length == directions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= positions[i] &lt;= height</code></li>
	<li><code>directions[i]</code>&nbsp;为&nbsp;<code>'U'</code>&nbsp;或&nbsp;<code>'D'</code>。</li>
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
