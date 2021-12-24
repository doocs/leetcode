# [391. 完美矩形](https://leetcode-cn.com/problems/perfect-rectangle)

[English Version](/solution/0300-0399/0391.Perfect%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有 N 个与坐标轴对齐的矩形, 其中 N &gt; 0, 判断它们是否能精确地覆盖一个矩形区域。</p>

<p>每个矩形用左下角的点和右上角的点的坐标来表示。例如，&nbsp;一个单位正方形可以表示为 [1,1,2,2]。&nbsp;( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2) )。</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0391.Perfect%20Rectangle/images/rectangle_perfect.gif"></p>

<p><strong>示例 1:</strong></p>

<pre>rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
</pre>

<p>&nbsp;</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0391.Perfect%20Rectangle/images/rectangle_separated.gif"></p>

<p><strong>示例&nbsp;2:</strong></p>

<pre>rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
</pre>

<p>&nbsp;</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0391.Perfect%20Rectangle/images/rectangle_hole.gif"></p>

<p><strong>示例 3:</strong></p>

<pre>rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
</pre>

<p>&nbsp;</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0391.Perfect%20Rectangle/images/rectangle_intersect.gif"></p>

<p><strong>示例 4:</strong></p>

<pre>rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用哈希表统计小矩形顶点出现的次数，除了最终大矩形的四个顶点只出现 1 次外，其他顶点的出现次数只有可能是 2 或 4。另外，为了满足条件，小矩形的面积和必须等于大矩形（无重叠）

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isRectangleCover(self, rectangles: List[List[int]]) -> bool:
        area = 0
        minX, minY = rectangles[0][0], rectangles[0][1]
        maxX, maxY = rectangles[0][2], rectangles[0][3]
        cnt = defaultdict(int)

        for r in rectangles:
            area += (r[2] - r[0]) * (r[3] - r[1])

            minX = min(minX, r[0])
            minY = min(minY, r[1])
            maxX = max(maxX, r[2])
            maxY = max(maxY, r[3])

            cnt[(r[0], r[1])] += 1
            cnt[(r[0], r[3])] += 1
            cnt[(r[2], r[3])] += 1
            cnt[(r[2], r[1])] += 1

        if (
            area != (maxX - minX) * (maxY - minY)
            or cnt[(minX, minY)] != 1
            or cnt[(minX, maxY)] != 1
            or cnt[(maxX, maxY)] != 1
            or cnt[(maxX, minY)] != 1
        ):
            return False

        del cnt[(minX, minY)], cnt[(minX, maxY)], cnt[(maxX, maxY)], cnt[(maxX, minY)]

        return all(c == 2 or c == 4 for c in cnt.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1];
        int maxX = rectangles[0][2], maxY = rectangles[0][3];
        Map<Pair, Integer> cnt = new HashMap<>();

        for (int[] r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);

            minX = Math.min(minX, r[0]);
            minY = Math.min(minY, r[1]);
            maxX = Math.max(maxX, r[2]);
            maxY = Math.max(maxY, r[3]);

            cnt.merge(new Pair(r[0], r[1]), 1, Integer::sum);
            cnt.merge(new Pair(r[0], r[3]), 1, Integer::sum);
            cnt.merge(new Pair(r[2], r[3]), 1, Integer::sum);
            cnt.merge(new Pair(r[2], r[1]), 1, Integer::sum);
        }

        if (area != (long) (maxX - minX) * (maxY - minY)
                || cnt.getOrDefault(new Pair(minX, minY), 0) != 1
                || cnt.getOrDefault(new Pair(minX, maxY), 0) != 1
                || cnt.getOrDefault(new Pair(maxX, maxY), 0) != 1
                || cnt.getOrDefault(new Pair(maxX, minY), 0) != 1) {
            return false;
        }

        cnt.remove(new Pair(minX, minY));
        cnt.remove(new Pair(minX, maxY));
        cnt.remove(new Pair(maxX, maxY));
        cnt.remove(new Pair(maxX, minY));

        return cnt.values().stream().allMatch(c -> c == 2 || c == 4);
    }

    private static class Pair {
        final int first;
        final int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {
        long long area = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1];
        int maxX = rectangles[0][2], maxY = rectangles[0][3];

        using pii = pair<int, int>;
        map<pii, int> cnt;

        for (auto& r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);

            minX = min(minX, r[0]);
            minY = min(minY, r[1]);
            maxX = max(maxX, r[2]);
            maxY = max(maxY, r[3]);

            ++cnt[{r[0], r[1]}];
            ++cnt[{r[0], r[3]}];
            ++cnt[{r[2], r[3]}];
            ++cnt[{r[2], r[1]}];
        }

        if (area != (long long)(maxX - minX) * (maxY - minY) ||
            cnt[{minX, minY}] != 1 || cnt[{minX, maxY}] != 1 ||
            cnt[{maxX, maxY}] != 1 || cnt[{maxX, minY}] != 1) {
            return false;
        }

        cnt.erase({minX, minY});
        cnt.erase({minX, maxY});
        cnt.erase({maxX, maxY});
        cnt.erase({maxX, minY});

        return all_of(cnt.begin(), cnt.end(), [](pair<pii, int> e) {
            return e.second == 2 || e.second == 4;
        });
    }
};
```

### **Go**

```go
type pair struct {
	first  int
	second int
}

func isRectangleCover(rectangles [][]int) bool {
	area := 0
	minX, minY := rectangles[0][0], rectangles[0][1]
	maxX, maxY := rectangles[0][2], rectangles[0][3]

	cnt := make(map[pair]int)
	for _, r := range rectangles {
		area += (r[2] - r[0]) * (r[3] - r[1])

		minX = min(minX, r[0])
		minY = min(minY, r[1])
		maxX = max(maxX, r[2])
		maxY = max(maxY, r[3])

		cnt[pair{r[0], r[1]}]++
		cnt[pair{r[0], r[3]}]++
		cnt[pair{r[2], r[3]}]++
		cnt[pair{r[2], r[1]}]++
	}

	if area != (maxX-minX)*(maxY-minY) ||
		cnt[pair{minX, minY}] != 1 ||
		cnt[pair{minX, maxY}] != 1 ||
		cnt[pair{maxX, maxY}] != 1 ||
		cnt[pair{maxX, minY}] != 1 {
		return false
	}

	delete(cnt, pair{minX, minY})
	delete(cnt, pair{minX, maxY})
	delete(cnt, pair{maxX, maxY})
	delete(cnt, pair{maxX, minY})

	for _, c := range cnt {
		if c != 2 && c != 4 {
			return false
		}
	}
	return true
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
