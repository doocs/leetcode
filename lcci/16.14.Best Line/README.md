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
> 过点数最多的直线，返回其上最小的两个下标。点的数量通常不大，三重枚举 $O(n^3)$ 可过。
>
> 固定两点定直线，第三点用叉积 $(y_2-y_1)(x_3-x_1)=(y_3-y_1)(x_2-x_1)$ 判断共线，避开除法精度。
>
> 枚举 $i<j<k$，用计数更新最大值及对应的 $(i,j)$。先枚举较小下标，自然满足「编号最小」的次序。

<!-- thinking:end -->

我们可以枚举任意两个点 $(x_1, y_1), (x_2, y_2)$，把这两个点连成一条直线，那么此时这条直线上的点的个数就是 2，接下来我们再枚举其他点 $(x_3, y_3)$，判断它们是否在同一条直线上，如果在，那么直线上的点的个数就加 1，如果不在，那么直线上的点的个数不变。找出所有直线上的点的个数的最大值，其对应的最小的两个点的编号即为答案。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `points` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                cnt = 2
                for k in range(j + 1, n):
                    x3, y3 = points[k]
                    a = (y2 - y1) * (x3 - x1)
                    b = (y3 - y1) * (x2 - x1)
                    cnt += a == b
                if mx < cnt:
                    mx = cnt
                    x, y = i, j
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
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    int a = (y2 - y1) * (x3 - x1);
                    int b = (y3 - y1) * (x2 - x1);
                    if (a == b) {
                        ++cnt;
                    }
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
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
        vector<int> ans(2);
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    long a = (long) (y2 - y1) * (x3 - x1);
                    long b = (long) (y3 - y1) * (x2 - x1);
                    cnt += a == b;
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
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
	ans := make([]int, 2)
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			cnt := 2
			for k := j + 1; k < n; k++ {
				x3, y3 := points[k][0], points[k][1]
				a := (y2 - y1) * (x3 - x1)
				b := (y3 - y1) * (x2 - x1)
				if a == b {
					cnt++
				}
			}
			if mx < cnt {
				mx = cnt
				ans[0], ans[1] = i, j
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
        var maxCount = 0
        var answer = [Int](repeating: 0, count: 2)

        for i in 0..<n {
            let x1 = points[i][0], y1 = points[i][1]
            for j in i + 1..<n {
                let x2 = points[j][0], y2 = points[j][1]
                var count = 2

                for k in j + 1..<n {
                    let x3 = points[k][0], y3 = points[k][1]
                    let a = (y2 - y1) * (x3 - x1)
                    let b = (y3 - y1) * (x2 - x1)
                    if a == b {
                        count += 1
                    }
                }

                if maxCount < count {
                    maxCount = count
                    answer = [i, j]
                }
            }
        }
        return answer
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
> 若把约分后的 $(\mathrm{d}x,\mathrm{d}y)$ 直接当作键，重合点会使最大公约数为 $0$，相反方向也会把同一条直线拆开。
>
> 锚点落在线段中部时，左右两侧的最简斜率互为相反数，每一侧的计数都小于真实点数，返回的下标对随之偏离。
>
> 重合点落在通过该锚点的每一条直线上，相反方向表示同一条直线，应当共用一个键。
>
> 因此我们先把 $(0,0)$ 收进重合列表，再把约分后的方向规范成 $\mathrm{d}x>0$（竖直方向则 $\mathrm{d}y>0$），并用该键收集下标。点数为锚点、该方向上的点与重合点之和，第二下标取其中的最小者。

<!-- thinking:end -->

我们可以枚举锚点 $(x_1, y_1)$，把其余点相对它的方向写入哈希表。与锚点重合的点没有斜率，单独记录，并计入每一条通过该锚点的直线。对其余点，将差 $(\mathrm{d}x, \mathrm{d}y)$ 用最大公约数约成最简分数，再把相反方向并到同一个键上：保证 $\mathrm{d}x > 0$，若 $\mathrm{d}x = 0$ 则保证 $\mathrm{d}y > 0$。键相同的点与锚点、重合点共线。该直线上的点数等于 $1$ 加上该键中的点数与重合点数，第二下标取这些点里的最小下标。锚点按下标从小到大枚举，只在点数严格变大，或点数相同且下标对更小时更新答案。

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
        x = y = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            dup = []
            for j in range(i + 1, n):
                dx, dy = points[j][0] - x1, points[j][1] - y1
                if dx == 0 and dy == 0:
                    dup.append(j)
                    continue
                g = gcd(dx, dy)
                dx //= g
                dy //= g
                if dx < 0 or (dx == 0 and dy < 0):
                    dx, dy = -dx, -dy
                cnt[(dx, dy)].append(j)
            groups = (
                [js + dup for js in cnt.values()] if cnt else ([dup] if dup else [])
            )
            for js in groups:
                c = len(js) + 1
                b = min(js)
                if c > mx or (c == mx and (i, b) < (x, y)):
                    mx = c
                    x, y = i, b
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
            Map<String, List<Integer>> cnt = new HashMap<>();
            List<Integer> dup = new ArrayList<>();
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.add(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                String key = dx + "." + dy;
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(j);
            }
            if (cnt.isEmpty()) {
                if (!dup.isEmpty()) {
                    int c = dup.size() + 1;
                    if (better(mx, ans, c, i, dup.get(0))) {
                        mx = c;
                        ans[0] = i;
                        ans[1] = dup.get(0);
                    }
                }
                continue;
            }
            for (List<Integer> js : cnt.values()) {
                int b = js.get(0);
                if (!dup.isEmpty()) {
                    b = Math.min(b, dup.get(0));
                }
                int c = js.size() + dup.size() + 1;
                if (better(mx, ans, c, i, b)) {
                    mx = c;
                    ans[0] = i;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }

    private boolean better(int mx, int[] ans, int c, int a, int b) {
        return c > mx || (c == mx && (a < ans[0] || (a == ans[0] && b < ans[1])));
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
            unordered_map<string, vector<int>> cnt;
            vector<int> dup;
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.push_back(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                string k = to_string(dx) + "." + to_string(dy);
                cnt[k].push_back(j);
            }
            auto consider = [&](int b, int c) {
                if (c > mx || (c == mx && ans > pair<int, int>{i, b})) {
                    mx = c;
                    ans = {i, b};
                }
            };
            if (cnt.empty()) {
                if (!dup.empty()) {
                    consider(dup[0], (int) dup.size() + 1);
                }
                continue;
            }
            for (auto& e : cnt) {
                int b = e.second[0];
                if (!dup.empty()) {
                    b = min(b, dup[0]);
                }
                consider(b, (int) e.second.size() + (int) dup.size() + 1);
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
	ans := []int{0, 0}
	type pair struct{ x, y int }
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		cnt := map[pair][]int{}
		var dup []int
		for j := i + 1; j < n; j++ {
			dx, dy := points[j][0]-x1, points[j][1]-y1
			if dx == 0 && dy == 0 {
				dup = append(dup, j)
				continue
			}
			g := gcd(dx, dy)
			dx /= g
			dy /= g
			if dx < 0 || (dx == 0 && dy < 0) {
				dx, dy = -dx, -dy
			}
			k := pair{dx, dy}
			cnt[k] = append(cnt[k], j)
		}
		consider := func(b, c int) {
			if c > mx || (c == mx && (i < ans[0] || (i == ans[0] && b < ans[1]))) {
				mx = c
				ans[0], ans[1] = i, b
			}
		}
		if len(cnt) == 0 {
			if len(dup) > 0 {
				consider(dup[0], len(dup)+1)
			}
			continue
		}
		for _, js := range cnt {
			b := js[0]
			if len(dup) > 0 && dup[0] < b {
				b = dup[0]
			}
			consider(b, len(js)+len(dup)+1)
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
