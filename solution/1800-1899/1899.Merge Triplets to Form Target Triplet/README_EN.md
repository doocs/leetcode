# [1899. Merge Triplets to Form Target Triplet](https://leetcode.com/problems/merge-triplets-to-form-target-triplet)

[中文文档](/solution/1800-1899/1899.Merge%20Triplets%20to%20Form%20Target%20Triplet/README.md)

## Description

<p>A <strong>triplet</strong> is an array of three integers. You are given a 2D integer array <code>triplets</code>, where <code>triplets[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>]</code> describes the <code>i<sup>th</sup></code> <strong>triplet</strong>. You are also given an integer array <code>target = [x, y, z]</code> that describes the <strong>triplet</strong> you want to obtain.</p>

<p>To obtain <code>target</code>, you may apply the following operation on <code>triplets</code> <strong>any number</strong> of times (possibly <strong>zero</strong>):</p>

<ul>
	<li>Choose two indices (<strong>0-indexed</strong>) <code>i</code> and <code>j</code> (<code>i != j</code>) and <strong>update</strong> <code>triplets[j]</code> to become <code>[max(a<sub>i</sub>, a<sub>j</sub>), max(b<sub>i</sub>, b<sub>j</sub>), max(c<sub>i</sub>, c<sub>j</sub>)]</code>.
    <ul>
    	<li>For example, if <code>triplets[i] = [2, 5, 3]</code> and <code>triplets[j] = [1, 7, 5]</code>, <code>triplets[j]</code> will be updated to <code>[max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5]</code>.</li>
    </ul>
    </li>
</ul>

<p>Return <code>true</code> <em>if it is possible to obtain the </em><code>target</code><em> <strong>triplet</strong> </em><code>[x, y, z]</code><em> as an<strong> element</strong> of </em><code>triplets</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> Perform the following operations:
- Choose the first and last triplets [<u>[2,5,3]</u>,[1,8,4],<u>[1,7,5]</u>]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],<u>[2,7,5]</u>]
The target triplet [2,7,5] is now an element of triplets.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
<strong>Output:</strong> true
<strong>Explanation: </strong>Perform the following operations:
- Choose the first and third triplets [<u>[2,5,3]</u>,[2,3,4],<u>[1,2,5]</u>,[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],<u>[2,5,5]</u>,[5,2,3]].
- Choose the third and fourth triplets [[2,5,3],[2,3,4],<u>[2,5,5]</u>,<u>[5,2,3]</u>]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],<u>[5,5,5]</u>].
The target triplet [5,5,5] is now an element of triplets.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= triplets.length &lt;= 10<sup>5</sup></code></li>
	<li><code>triplets[i].length == target.length == 3</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, x, y, z &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        x, y, z = target
        d = e = f = 0
        for a, b, c in triplets:
            if a <= x and b <= y and c <= z:
                d = max(d, a)
                e = max(e, b)
                f = max(f, c)
        return [d, e, f] == target
```

### **Java**

```java
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int x = target[0], y = target[1], z = target[2];
        int d = 0, e = 0, f = 0;
        for (var t : triplets) {
            int a = t[0], b = t[1], c = t[2];
            if (a <= x && b <= y && c <= z) {
                d = Math.max(d, a);
                e = Math.max(e, b);
                f = Math.max(f, c);
            }
        }
        return d == x && e == y && f == z;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool mergeTriplets(vector<vector<int>>& triplets, vector<int>& target) {
        int x = target[0], y = target[1], z = target[2];
        int d = 0, e = 0, f = 0;
        for (auto& t : triplets) {
            int a = t[0], b = t[1], c = t[2];
            if (a <= x && b <= y && c <= z) {
                d = max(d, a);
                e = max(e, b);
                f = max(f, c);
            }
        }
        return d == x && e == y && f == z;
    }
};
```

### **Go**

```go
func mergeTriplets(triplets [][]int, target []int) bool {
	x, y, z := target[0], target[1], target[2]
	d, e, f := 0, 0, 0
	for _, t := range triplets {
		a, b, c := t[0], t[1], t[2]
		if a <= x && b <= y && c <= z {
			d = max(d, a)
			e = max(e, b)
			f = max(f, c)
		}
	}
	return d == x && e == y && f == z
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function mergeTriplets(triplets: number[][], target: number[]): boolean {
    const [x, y, z] = target;
    let [d, e, f] = [0, 0, 0];
    for (const [a, b, c] of triplets) {
        if (a <= x && b <= y && c <= z) {
            d = Math.max(d, a);
            e = Math.max(e, b);
            f = Math.max(f, c);
        }
    }
    return d === x && e === y && f === z;
}
```

### **...**

```

```

<!-- tabs:end -->
