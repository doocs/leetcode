---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README_EN.md
---

<!-- problem:start -->

# [2753. Count Houses in a Circular Street II 🔒](https://leetcode.com/problems/count-houses-in-a-circular-street-ii)

[中文文档](/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an object <code>street</code> of class <code>Street</code> that represents a <strong>circular</strong> street and a positive integer <code>k</code> which represents a maximum bound for the number of houses in that street (in other words, the number of houses is less than or equal to <code>k</code>). Houses&#39; doors could be open or closed initially (at least one is open).</p>

<p>Initially, you are standing in front of a door to a house on this street. Your task is to count the number of houses in the street.</p>

<p>The class <code>Street</code> contains the following functions which may help you:</p>

<ul>
	<li><code>void closeDoor()</code>: Close the door of the house you are in front of.</li>
	<li><code>boolean isDoorOpen()</code>: Returns <code>true</code> if the door of the current house is open and <code>false</code> otherwise.</li>
	<li><code>void moveRight()</code>: Move to the right house.</li>
</ul>

<p><strong>Note</strong> that by <strong>circular</strong> street, we mean if you number the houses from <code>1</code> to <code>n</code>, then the right house of <code>house<sub>i</sub></code> is <code>house<sub>i+1</sub></code> for <code>i &lt; n</code>, and the right house of <code>house<sub>n</sub></code> is <code>house<sub>1</sub></code>.</p>

<p>Return <code>ans</code> <em>which represents the number of houses on this street.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> street = [1,1,1,1], k = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 houses, and all their doors are open. 
The number of houses is less than k, which is 10.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> street = [1,0,1,1,0], k = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 houses, and the doors of the 1st, 3rd, and 4th house (moving in the right direction) are open, and the rest are closed.
The number of houses is equal to k, which is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == number of houses</code></li>
	<li><code>1 &lt;= n &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>street</code> is circular by definition provided in the statement.</li>
	<li>The input is generated such that at least one of the doors is open.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
# Definition for a street.
# class Street:
#     def closeDoor(self):
#         pass
#     def isDoorOpen(self):
#         pass
#     def moveRight(self):
#         pass
class Solution:
    def houseCount(self, street: Optional["Street"], k: int) -> int:
        while not street.isDoorOpen():
            street.moveRight()
        for i in range(1, k + 1):
            street.moveRight()
            if street.isDoorOpen():
                ans = i
                street.closeDoor()
        return ans
```

#### Java

```java
/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while (!street.isDoorOpen()) {
            street.moveRight();
        }
        int ans = 0;
        for (int i = 1; i <= k; ++i) {
            street.moveRight();
            if (street.isDoorOpen()) {
                ans = i;
                street.closeDoor();
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
/**
 * Definition for a street.
 * class Street {
 * public:
 *     Street(vector<int> doors);
 *     void closeDoor();
 *     bool isDoorOpen();
 *     void moveRight();
 * };
 */
class Solution {
public:
    int houseCount(Street* street, int k) {
        while (!street->isDoorOpen()) {
            street->moveRight();
        }
        int ans = 0;
        for (int i = 1; i <= k; ++i) {
            street->moveRight();
            if (street->isDoorOpen()) {
                ans = i;
                street->closeDoor();
            }
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a street.
 * type Street interface {
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for !street.IsDoorOpen() {
		street.MoveRight()
	}
	for i := 1; i <= k; i++ {
		street.MoveRight()
		if street.IsDoorOpen() {
			ans = i
			street.CloseDoor()
		}
	}
	return
}
```

#### TypeScript

```ts
/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (!street.isDoorOpen()) {
        street.moveRight();
    }
    let ans = 0;
    for (let i = 1; i <= k; ++i) {
        street.moveRight();
        if (street.isDoorOpen()) {
            ans = i;
            street.closeDoor();
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
