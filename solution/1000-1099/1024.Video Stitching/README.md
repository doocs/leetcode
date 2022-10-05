# [1024. 视频拼接](https://leetcode.cn/problems/video-stitching)

[English Version](/solution/1000-1099/1024.Video%20Stitching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你将会获得一系列视频片段，这些片段来自于一项持续时长为&nbsp;<code>time</code>&nbsp;秒的体育赛事。这些片段可能有所重叠，也可能长度不一。</p>

<p>使用数组&nbsp;<code>clips</code> 描述所有的视频片段，其中 <code>clips[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示：某个视频片段开始于&nbsp;<code>start<sub>i</sub></code>&nbsp;并于&nbsp;<code>end<sub>i</sub></code>&nbsp;结束。</p>

<p>甚至可以对这些片段自由地再剪辑：</p>

<ul>
	<li>例如，片段&nbsp;<code>[0, 7]</code>&nbsp;可以剪切成&nbsp;<code>[0, 1] +&nbsp;[1, 3] + [3, 7]</code>&nbsp;三部分。</li>
</ul>

<p>我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（<code>[0, time]</code>）。返回所需片段的最小数目，如果无法完成该任务，则返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
<strong>输出：</strong>3
<strong>解释：</strong>
选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,1],[1,2]], time = 5
<strong>输出：</strong>-1
<strong>解释：</strong>
无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
<strong>输出：</strong>3
<strong>解释： </strong>
选取片段 [0,4], [4,7] 和 [6,9] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= clips.length &lt;= 100</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= time &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

注意到，如果相同起点的子区间有多个，那么选择右端点最大的那个子区间是最优的。

因此，我们可以预处理所有子区间，对于每一个位置 $i$，算出所有以 $i$ 为起点的子区间中，右端点最大的那个位置，记录在数组 $last[i]$ 中。

我们定义变量 `mx` 表示当前能够到达的最远位置，变量 `ans` 表示当前需要的最少子区间数，变量 `pre` 表示上一个被使用的子区间的右端点。

接下来，我们从 $0$ 开始枚举所有位置 $i$，用 $last[i]$ 来更新 `mx`。如果更新后 $mx = i$，说明无法覆盖下一个位置，因此无法完成任务，返回 $-1$。

同时我们记录上一个被使用的子区间的右端点 `pre`，如果 $pre = i$，说明需要使用一个新的子区间，因此我们将 `ans` 加 $1$，并将 `pre` 更新为 `mx`。

遍历结束后，返回 `ans` 即可。

时间复杂度 $O(n+m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别是数组 `clips` 的长度和 `time` 的值。

相似题目：

-   [45. 跳跃游戏 II](/solution/0000-0099/0045.Jump%20Game%20II/README.md)
-   [55. 跳跃游戏](/solution/0000-0099/0055.Jump%20Game/README.md)
-   [1326. 灌溉花园的最少水龙头数目](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def videoStitching(self, clips: List[List[int]], time: int) -> int:
        last = [0] * time
        for a, b in clips:
            if a < time:
                last[a] = max(last[a], b)
        ans = mx = pre = 0
        for i, v in enumerate(last):
            mx = max(mx, v)
            if mx <= i:
                return -1
            if pre == i:
                ans += 1
                pre = mx
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int videoStitching(int[][] clips, int time) {
        int[] last = new int[time];
        for (var e : clips) {
            int a = e[0], b = e[1];
            if (a < time) {
                last[a] = Math.max(last[a], b);
            }
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < time; ++i) {
            mx = Math.max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int videoStitching(vector<vector<int>>& clips, int time) {
        vector<int> last(time);
        for (auto& v : clips) {
            int a = v[0], b = v[1];
            if (a < time) {
                last[a] = max(last[a], b);
            }
        }
        int mx = 0, ans = 0;
        int pre = 0;
        for (int i = 0; i < time; ++i) {
            mx = max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func videoStitching(clips [][]int, time int) int {
	last := make([]int, time)
	for _, v := range clips {
		a, b := v[0], v[1]
		if a < time {
			last[a] = max(last[a], b)
		}
	}
	ans, mx, pre := 0, 0, 0
	for i, v := range last {
		mx = max(mx, v)
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
