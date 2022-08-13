# [2250. 统计包含每个点的矩形数目](https://leetcode.cn/problems/count-number-of-rectangles-containing-each-point)

[English Version](/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>rectangles</code>&nbsp;，其中&nbsp;<code>rectangles[i] = [l<sub>i</sub>, h<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个矩形长为&nbsp;<code>l<sub>i</sub></code>&nbsp;高为&nbsp;<code>h<sub>i</sub></code>&nbsp;。给你一个二维整数数组&nbsp;<code>points</code>&nbsp;，其中&nbsp;<code>points[j] = [x<sub>j</sub>, y<sub>j</sub>]</code>&nbsp;是坐标为&nbsp;<code>(x<sub>j</sub>, y<sub>j</sub>)</code>&nbsp;的一个点。</p>

<p>第&nbsp;<code>i</code>&nbsp;个矩形的 <strong>左下角</strong>&nbsp;在&nbsp;<code>(0, 0)</code>&nbsp;处，<strong>右上角</strong>&nbsp;在&nbsp;<code>(l<sub>i</sub>, h<sub>i</sub>)</code>&nbsp;。</p>

<p>请你返回一个整数数组<em>&nbsp;</em><code>count</code>&nbsp;，长度为&nbsp;<code>points.length</code>，其中<em>&nbsp;</em><code>count[j]</code>是 <strong>包含</strong> 第<em>&nbsp;</em><code>j</code>&nbsp;个点的矩形数目。</p>

<p>如果&nbsp;<code>0 &lt;= x<sub>j</sub> &lt;= l<sub>i</sub></code> 且&nbsp;<code>0 &lt;= y<sub>j</sub> &lt;= h<sub>i</sub></code>&nbsp;，那么我们说第&nbsp;<code>i</code>&nbsp;个矩形包含第&nbsp;<code>j</code>&nbsp;个点。如果一个点刚好在矩形的 <strong>边上</strong>&nbsp;，这个点也被视为被矩形包含。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/images/example1.png" style="width: 300px; height: 509px;"></p>

<pre><b>输入：</b>rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
<b>输出：</b>[2,1]
<b>解释：</b>
第一个矩形不包含任何点。
第二个矩形只包含一个点 (2, 1) 。
第三个矩形包含点 (2, 1) 和 (1, 4) 。
包含点 (2, 1) 的矩形数目为 2 。
包含点 (1, 4) 的矩形数目为 1 。
所以，我们返回 [2, 1] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/images/example2.png" style="width: 300px; height: 312px;"></p>

<pre><b>输入：</b>rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
<b>输出：</b>[1,3]
<strong>解释：
</strong>第一个矩形只包含点 (1, 1) 。
第二个矩形只包含点 (1, 1) 。
第三个矩形包含点 (1, 3) 和 (1, 1) 。
包含点 (1, 3) 的矩形数目为 1 。
包含点 (1, 1) 的矩形数目为 3 。
所以，我们返回 [1, 3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length, points.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>rectangles[i].length == points[j].length == 2</code></li>
	<li><code>1 &lt;= l<sub>i</sub>, x<sub>j</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= h<sub>i</sub>, y<sub>j</sub> &lt;= 100</code></li>
	<li>所有&nbsp;<code>rectangles</code>&nbsp;<strong>互不相同</strong>&nbsp;。</li>
	<li>所有&nbsp;<code>points</code> <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countRectangles(
        self, rectangles: List[List[int]], points: List[List[int]]
    ) -> List[int]:
        d = defaultdict(list)
        for x, y in rectangles:
            d[y].append(x)
        for y in d.keys():
            d[y].sort()
        ans = []
        for x, y in points:
            cnt = 0
            for h in range(y, 101):
                xs = d[h]
                cnt += len(xs) - bisect_left(xs, x)
            ans.append(cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = 101;
        List<Integer>[] d = new List[n];
        for (int i = 0; i < n; ++i) {
            d[i] = new ArrayList<>();
        }
        for (int[] r : rectangles) {
            d[r[1]].add(r[0]);
        }
        for (List<Integer> v : d) {
            Collections.sort(v);
        }
        int m = points.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = points[i][0], y = points[i][1];
            int cnt = 0;
            for (int h = y; h < n; ++h) {
                List<Integer> xs = d[h];
                int left = 0, right = xs.size();
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (xs.get(mid) >= x) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                cnt += xs.size() - left;
            }
            ans[i] = cnt;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countRectangles(rectangles: number[][], points: number[][]): number[] {
    const n = 101;
    let ymap = Array.from({ length: n }, v => []);
    for (let [x, y] of rectangles) {
        ymap[y].push(x);
    }
    for (let nums of ymap) {
        nums.sort((a, b) => a - b);
    }
    let ans = [];
    for (let [x, y] of points) {
        let count = 0;
        for (let h = y; h < n; h++) {
            const nums = ymap[h];
            let left = 0,
                right = nums.length;
            while (left < right) {
                let mid = (left + right) >> 1;
                if (x > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            count += nums.length - right;
        }
        ans.push(count);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> countRectangles(vector<vector<int>>& rectangles, vector<vector<int>>& points) {
        int n = 101;
        vector<vector<int>> d(n);
        for (auto& r : rectangles) d[r[1]].push_back(r[0]);
        for (auto& v : d) sort(v.begin(), v.end());
        vector<int> ans;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            int cnt = 0;
            for (int h = y; h < n; ++h) {
                auto& xs = d[h];
                cnt += xs.size() - (lower_bound(xs.begin(), xs.end(), x) - xs.begin());
            }
            ans.push_back(cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func countRectangles(rectangles [][]int, points [][]int) []int {
	n := 101
	d := make([][]int, 101)
	for _, r := range rectangles {
		d[r[1]] = append(d[r[1]], r[0])
	}
	for _, v := range d {
		sort.Ints(v)
	}
	var ans []int
	for _, p := range points {
		x, y := p[0], p[1]
		cnt := 0
		for h := y; h < n; h++ {
			xs := d[h]
			left, right := 0, len(xs)
			for left < right {
				mid := (left + right) >> 1
				if xs[mid] >= x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += len(xs) - left
		}
		ans = append(ans, cnt)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
