---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [面试题 16.14. 最佳直线](https://leetcode.cn/problems/best-line-lcci)

[English Version](/lcci/16.14.Best%20Line/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二维平面及平面上的 N 个点列表<code>Points</code>，其中第<code>i</code>个点的坐标为<code>Points[i]=[X<sub>i</sub>,Y<sub>i</sub>]</code>。请找出一条直线，其通过的点的数目最多。</p>
<p>设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为<code>S</code>，你仅需返回<code>[S[0],S[1]]</code>作为答案，若有多条直线穿过了相同数量的点，则选择<code>S[0]</code>值较小的直线返回，<code>S[0]</code>相同则选择<code>S[1]</code>值较小的直线返回。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> [[0,0],[1,1],[1,0],[2,0]]
<strong>输出：</strong> [0,2]
<strong>解释：</strong> 所求直线穿过的3个点的编号为[0,2,3]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>2 <= len(Points) <= 300</code></li>
<li><code>len(Points[i]) = 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：暴力枚举

<!-- thinking:start -->

> **思考**
>
> 两点重合时，叉积两侧都是 $0$，后面的每个点都会被算成共线。
>
> 这样得到的点数大于真实直线，返回的下标对也就不再是点数最多的直线上最小的两个编号。
>
> 直线要由两个位置不同的点确定，重合点落在通过该位置的每一条直线上，应当计入点集。
>
> 因此我们跳过重合的点对，用叉积扫描全部下标，只保留最先命中的两个作为候选。所有点都重合时，没有位置不同的点对，答案是 $[0, 1]$。

<!-- thinking:end -->

我们可以枚举两个位置不同的点 $(x_1, y_1)$ 与 $(x_2, y_2)$ 来确定一条直线。对每个下标 $k$，用叉积 $(y_2-y_1)(x_3-x_1)=(y_3-y_1)(x_2-x_1)$ 判断点 $k$ 是否在这条直线上。重合点与任一端点坐标相同，叉积为 $0$，会自然计入。记下这条直线上的点数，以及其中最小的两个下标。点数严格更大，或点数相同且下标对更小时更新答案。若所有点坐标都相同，不存在位置不同的点对，答案为 $[0, 1]$。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `points` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        n = len(points)
        mx = 0
        x, y = 0, 1
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                if x1 == x2 and y1 == y2:
                    continue
                cnt = 0
                a = b = -1
                for k in range(n):
                    x3, y3 = points[k]
                    c1 = (y2 - y1) * (x3 - x1)
                    c2 = (y3 - y1) * (x2 - x1)
                    if c1 == c2:
                        cnt += 1
                        if a < 0:
                            a = k
                        elif b < 0:
                            b = k
                if cnt > mx or (cnt == mx and (a, b) < (x, y)):
                    mx = cnt
                    x, y = a, b
        return [x, y]
```

#### Java

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = {0, 1};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    continue;
                }
                int cnt = 0;
                int a = -1, b = -1;
                for (int k = 0; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    int c1 = (y2 - y1) * (x3 - x1);
                    int c2 = (y3 - y1) * (x2 - x1);
                    if (c1 == c2) {
                        ++cnt;
                        if (a < 0) {
                            a = k;
                        } else if (b < 0) {
                            b = k;
                        }
                    }
                }
                if (cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1])))) {
                    mx = cnt;
                    ans[0] = a;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        vector<int> ans = {0, 1};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    continue;
                }
                int cnt = 0;
                int a = -1, b = -1;
                for (int k = 0; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    long c1 = (long) (y2 - y1) * (x3 - x1);
                    long c2 = (long) (y3 - y1) * (x2 - x1);
                    if (c1 == c2) {
                        ++cnt;
                        if (a < 0) {
                            a = k;
                        } else if (b < 0) {
                            b = k;
                        }
                    }
                }
                if (cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1])))) {
                    mx = cnt;
                    ans[0] = a;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func bestLine(points [][]int) []int {
	n := len(points)
	ans := []int{0, 1}
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			if x1 == x2 && y1 == y2 {
				continue
			}
			cnt := 0
			a, b := -1, -1
			for k := 0; k < n; k++ {
				x3, y3 := points[k][0], points[k][1]
				c1 := (y2 - y1) * (x3 - x1)
				c2 := (y3 - y1) * (x2 - x1)
				if c1 == c2 {
					cnt++
					if a < 0 {
						a = k
					} else if b < 0 {
						b = k
					}
				}
			}
			if cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1]))) {
				mx = cnt
				ans[0], ans[1] = a, b
			}
		}
	}
	return ans
}
```

#### Swift

```swift
class Solution {
    func bestLine(_ points: [[Int]]) -> [Int] {
        let n = points.count
        var mx = 0
        var ans = [0, 1]
        for i in 0..<n {
            let x1 = points[i][0], y1 = points[i][1]
            for j in i + 1..<n {
                let x2 = points[j][0], y2 = points[j][1]
                if x1 == x2 && y1 == y2 {
                    continue
                }
                var cnt = 0
                var a = -1
                var b = -1
                for k in 0..<n {
                    let x3 = points[k][0], y3 = points[k][1]
                    let c1 = (y2 - y1) * (x3 - x1)
                    let c2 = (y3 - y1) * (x2 - x1)
                    if c1 == c2 {
                        cnt += 1
                        if a < 0 {
                            a = k
                        } else if b < 0 {
                            b = k
                        }
                    }
                }
                if cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1]))) {
                    mx = cnt
                    ans = [a, b]
                }
            }
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：枚举 + 哈希表

<!-- thinking:start -->

> **思考**
>
> 三重循环在 $n$ 接近上限时偏紧，同一直线被重复计数。
>
> 固定一点，将其余点的斜率（约分后的分数）放入哈希表，相同斜率共线，复杂度降到 $O(n^2\log m)$。

<!-- thinking:end -->

我们可以枚举一个点 $(x_1, y_1)$，把其他所有点 $(x_2, y_2)$ 与 $(x_1, y_1)$ 连成的直线的斜率存入哈希表中，斜率相同的点在同一条直线上，哈希表的键为斜率，值为直线上的点的个数。找出哈希表中的最大值，即为答案。为了避免精度问题，我们可以将斜率 $\frac{y_2 - y_1}{x_2 - x_1}$ 进行约分，约分的方法是求最大公约数，然后分子分母同时除以最大公约数，将求得的分子分母作为哈希表的键。

时间复杂度 $O(n^2 \times \log m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 `points` 的长度和数组 `points` 所有横纵坐标差的最大值。

相似题目：

- [149. 直线上最多的点数](https://github.com/doocs/leetcode/blob/main/solution/0100-0199/0149.Max%20Points%20on%20a%20Line/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            for j in range(i + 1, n):
                x2, y2 = points[j]
                dx, dy = x2 - x1, y2 - y1
                g = gcd(dx, dy)
                k = (dx // g, dy // g)
                cnt[k].append((i, j))
                if mx < len(cnt[k]) or (mx == len(cnt[k]) and (x, y) > cnt[k][0]):
                    mx = len(cnt[k])
                    x, y = cnt[k][0]
        return [x, y]
```

#### Java

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, List<int[]>> cnt = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                String key = (dx / g) + "." + (dy / g);
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[] {i, j});
                if (mx < cnt.get(key).size()
                    || (mx == cnt.get(key).size()
                        && (ans[0] > cnt.get(key).get(0)[0]
                            || (ans[0] == cnt.get(key).get(0)[0]
                                && ans[1] > cnt.get(key).get(0)[1])))) {
                    mx = cnt.get(key).size();
                    ans = cnt.get(key).get(0);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        pair<int, int> ans = {0, 0};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            unordered_map<string, vector<pair<int, int>>> cnt;
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                string k = to_string(dx / g) + "." + to_string(dy / g);
                cnt[k].push_back({i, j});
                if (mx < cnt[k].size() || (mx == cnt[k].size() && ans > cnt[k][0])) {
                    mx = cnt[k].size();
                    ans = cnt[k][0];
                }
            }
        }
        return vector<int>{ans.first, ans.second};
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

#### Go

```go
func bestLine(points [][]int) []int {
	n := len(points)
	ans := make([]int, 2)
	type pair struct{ i, j int }
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		cnt := map[pair][]pair{}
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1
			g := gcd(dx, dy)
			k := pair{dx / g, dy / g}
			cnt[k] = append(cnt[k], pair{i, j})
			if mx < len(cnt[k]) || (mx == len(cnt[k]) && (ans[0] > cnt[k][0].i || (ans[0] == cnt[k][0].i && ans[1] > cnt[k][0].j))) {
				mx = len(cnt[k])
				ans[0], ans[1] = cnt[k][0].i, cnt[k][0].j
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
