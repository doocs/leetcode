# [1024. Video Stitching](https://leetcode.com/problems/video-stitching)

[中文文档](/solution/1000-1099/1024.Video%20Stitching/README.md)

## Description

<p>You are given a series of video clips from a sporting event that lasted <code>time</code> seconds. These video clips can be overlapping with each other and have varying lengths.</p>

<p>Each video clip is described by an array <code>clips</code> where <code>clips[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> indicates that the ith clip started at <code>start<sub>i</sub></code> and ended at <code>end<sub>i</sub></code>.</p>

<p>We can cut these clips into segments freely.</p>

<ul>
	<li>For example, a clip <code>[0, 7]</code> can be cut into segments <code>[0, 1] + [1, 3] + [3, 7]</code>.</li>
</ul>

<p>Return <em>the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event</em> <code>[0, time]</code>. If the task is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
<strong>Output:</strong> 3
<strong>Explanation:</strong> We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
Then, we can reconstruct the sporting event as follows:
We cut [1,9] into segments [1,2] + [2,8] + [8,9].
Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> clips = [[0,1],[1,2]], time = 5
<strong>Output:</strong> -1
<strong>Explanation:</strong> We cannot cover [0,5] with only [0,1] and [1,2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can take clips [0,4], [4,7], and [6,9].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= clips.length &lt;= 100</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= time &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
