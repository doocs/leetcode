---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3394.Check%20if%20Grid%20can%20be%20Cut%20into%20Sections/README_EN.md
rating: 1916
source: Biweekly Contest 146 Q3
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [3394. Check if Grid can be Cut into Sections](https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections)

[中文文档](/solution/3300-3399/3394.Check%20if%20Grid%20can%20be%20Cut%20into%20Sections/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the dimensions of an <code>n x n</code><!-- notionvc: fa9fe4ed-dff8-4410-8196-346f2d430795 --> grid, with the origin at the bottom-left corner of the grid. You are also given a 2D array of coordinates <code>rectangles</code>, where <code>rectangles[i]</code> is in the form <code>[start<sub>x</sub>, start<sub>y</sub>, end<sub>x</sub>, end<sub>y</sub>]</code>, representing a rectangle on the grid. Each rectangle is defined as follows:</p>

<ul>
	<li><code>(start<sub>x</sub>, start<sub>y</sub>)</code>: The bottom-left corner of the rectangle.</li>
	<li><code>(end<sub>x</sub>, end<sub>y</sub>)</code>: The top-right corner of the rectangle.</li>
</ul>

<p><strong>Note </strong>that the rectangles do not overlap. Your task is to determine if it is possible to make <strong>either two horizontal or two vertical cuts</strong> on the grid such that:</p>

<ul>
	<li>Each of the three resulting sections formed by the cuts contains <strong>at least</strong> one rectangle.</li>
	<li>Every rectangle belongs to <strong>exactly</strong> one section.</li>
</ul>

<p>Return <code>true</code> if such cuts can be made; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3394.Check%20if%20Grid%20can%20be%20Cut%20into%20Sections/images/tt1drawio.png" style="width: 285px; height: 280px;" /></p>

<p>The grid is shown in the diagram. We can make horizontal cuts at <code>y = 2</code> and <code>y = 4</code>. Hence, output is true.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3394.Check%20if%20Grid%20can%20be%20Cut%20into%20Sections/images/tc2drawio.png" style="width: 240px; height: 240px;" /></p>

<p>We can make vertical cuts at <code>x = 2</code> and <code>x = 3</code>. Hence, output is true.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>3 &lt;= rectangles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rectangles[i][0] &lt; rectangles[i][2] &lt;= n</code></li>
	<li><code>0 &lt;= rectangles[i][1] &lt; rectangles[i][3] &lt;= n</code></li>
	<li>No two rectangles overlap.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countLineIntersections(self, coordinates: List[tuple[int, int]]) -> bool:
        lines = 0
        overlap = 0
        for value, marker in coordinates:
            if marker == 0:
                overlap -= 1
            else:
                overlap += 1

            if overlap == 0:
                lines += 1

        return lines >= 3

    def checkValidCuts(self, n: int, rectangles: List[List[int]]) -> bool:
        y_coordinates = []
        x_coordinates = []

        for rect in rectangles:
            x1, y1, x2, y2 = rect
            y_coordinates.append((y1, 1))  # start
            y_coordinates.append((y2, 0))  # end

            x_coordinates.append((x1, 1))  # start
            x_coordinates.append((x2, 0))  # end

        # Sort by coordinate value, and for tie, put end (0) before start (1)
        y_coordinates.sort(key=lambda x: (x[0], x[1]))
        x_coordinates.sort(key=lambda x: (x[0], x[1]))

        return self.countLineIntersections(
            y_coordinates
        ) or self.countLineIntersections(x_coordinates)
```

#### Java

```java
class Solution {
    // Helper class to mimic C++ pair<int, int>
    static class Pair {
        int value;
        int type;

        Pair(int value, int type) {
            this.value = value;
            this.type = type;
        }
    }

    private boolean countLineIntersections(List<Pair> coordinates) {
        int lines = 0;
        int overlap = 0;

        for (Pair coord : coordinates) {
            if (coord.type == 0) {
                overlap--;
            } else {
                overlap++;
            }

            if (overlap == 0) {
                lines++;
            }
        }

        return lines >= 3;
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        List<Pair> yCoordinates = new ArrayList<>();
        List<Pair> xCoordinates = new ArrayList<>();

        for (int[] rectangle : rectangles) {
            // rectangle = [x1, y1, x2, y2]
            yCoordinates.add(new Pair(rectangle[1], 1)); // y1, start
            yCoordinates.add(new Pair(rectangle[3], 0)); // y2, end

            xCoordinates.add(new Pair(rectangle[0], 1)); // x1, start
            xCoordinates.add(new Pair(rectangle[2], 0)); // x2, end
        }

        Comparator<Pair> comparator = (a, b) -> {
            if (a.value != b.value) return Integer.compare(a.value, b.value);
            return Integer.compare(a.type, b.type); // End (0) before Start (1)
        };

        Collections.sort(yCoordinates, comparator);
        Collections.sort(xCoordinates, comparator);

        return countLineIntersections(yCoordinates) || countLineIntersections(xCoordinates);
    }
}
```

#### C++

```cpp
class Solution {
#define pii pair<int, int>

    bool countLineIntersections(vector<pii>& coordinates) {
        int lines = 0;
        int overlap = 0;
        for (int i = 0; i < coordinates.size(); ++i) {
            if (coordinates[i].second == 0)
                overlap--;
            else
                overlap++;
            if (overlap == 0)
                lines++;
        }
        return lines >= 3;
    }

public:
    bool checkValidCuts(int n, vector<vector<int>>& rectangles) {
        vector<pii> y_cordinates, x_cordinates;
        for (auto& rectangle : rectangles) {
            y_cordinates.push_back(make_pair(rectangle[1], 1));
            y_cordinates.push_back(make_pair(rectangle[3], 0));
            x_cordinates.push_back(make_pair(rectangle[0], 1));
            x_cordinates.push_back(make_pair(rectangle[2], 0));
        }
        sort(y_cordinates.begin(), y_cordinates.end());
        sort(x_cordinates.begin(), x_cordinates.end());

        // Line-Sweep on x and y cordinates
        return (countLineIntersections(y_cordinates) or countLineIntersections(x_cordinates));
    }
};
```

#### Go

```go
type Pair struct {
	val int
	typ int // 1 = start, 0 = end
}

func countLineIntersections(coords []Pair) bool {
	lines := 0
	overlap := 0
	for _, p := range coords {
		if p.typ == 0 {
			overlap--
		} else {
			overlap++
		}
		if overlap == 0 {
			lines++
		}
	}
	return lines >= 3
}

func checkValidCuts(n int, rectangles [][]int) bool {
	var xCoords []Pair
	var yCoords []Pair

	for _, rect := range rectangles {
		x1, y1, x2, y2 := rect[0], rect[1], rect[2], rect[3]

		yCoords = append(yCoords, Pair{y1, 1}) // start
		yCoords = append(yCoords, Pair{y2, 0}) // end

		xCoords = append(xCoords, Pair{x1, 1})
		xCoords = append(xCoords, Pair{x2, 0})
	}

	sort.Slice(yCoords, func(i, j int) bool {
		if yCoords[i].val == yCoords[j].val {
			return yCoords[i].typ < yCoords[j].typ // end before start
		}
		return yCoords[i].val < yCoords[j].val
	})

	sort.Slice(xCoords, func(i, j int) bool {
		if xCoords[i].val == xCoords[j].val {
			return xCoords[i].typ < xCoords[j].typ
		}
		return xCoords[i].val < xCoords[j].val
	})

	return countLineIntersections(yCoords) || countLineIntersections(xCoords)
}
```

#### TypeScript

```ts
function checkValidCuts(n: number, rectangles: number[][]): boolean {
    const check = (arr: number[][], getVals: (x: number[]) => number[]) => {
        let [c, longest] = [3, 0];

        for (const x of arr) {
            const [start, end] = getVals(x);

            if (start < longest) {
                longest = Math.max(longest, end);
            } else {
                longest = end;
                if (--c === 0) return true;
            }
        }

        return false;
    };

    const sortByX = ([a]: number[], [b]: number[]) => a - b;
    const sortByY = ([, a]: number[], [, b]: number[]) => a - b;
    const getX = ([x1, , x2]: number[]) => [x1, x2];
    const getY = ([, y1, , y2]: number[]) => [y1, y2];

    return check(rectangles.toSorted(sortByX), getX) || check(rectangles.toSorted(sortByY), getY);
}
```

#### JavaScript

```js
function checkValidCuts(n, rectangles) {
    const check = (arr, getVals) => {
        let [c, longest] = [3, 0];

        for (const x of arr) {
            const [start, end] = getVals(x);

            if (start < longest) {
                longest = Math.max(longest, end);
            } else {
                longest = end;
                if (--c === 0) return true;
            }
        }

        return false;
    };

    const sortByX = ([a], [b]) => a - b;
    const sortByY = ([, a], [, b]) => a - b;
    const getX = ([x1, , x2]) => [x1, x2];
    const getY = ([, y1, , y2]) => [y1, y2];

    return check(rectangles.toSorted(sortByX), getX) || check(rectangles.toSorted(sortByY), getY);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
