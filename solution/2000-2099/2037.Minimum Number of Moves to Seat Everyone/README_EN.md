# [2037. Minimum Number of Moves to Seat Everyone](https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone)

[中文文档](/solution/2000-2099/2037.Minimum%20Number%20of%20Moves%20to%20Seat%20Everyone/README.md)

## Description

<p>There are <code>n</code> seats and <code>n</code> students in a room. You are given an array <code>seats</code> of length <code>n</code>, where <code>seats[i]</code> is the position of the <code>i<sup>th</sup></code> seat. You are also given the array <code>students</code> of length <code>n</code>, where <code>students[j]</code> is the position of the <code>j<sup>th</sup></code> student.</p>

<p>You may perform the following move any number of times:</p>

<ul>
	<li>Increase or decrease the position of the <code>i<sup>th</sup></code> student by <code>1</code> (i.e., moving the <code>i<sup>th</sup></code> student from position&nbsp;<code>x</code>&nbsp;to <code>x + 1</code> or <code>x - 1</code>)</li>
</ul>

<p>Return <em>the <strong>minimum number of moves</strong> required to move each student to a seat</em><em> such that no two students are in the same seat.</em></p>

<p>Note that there may be <strong>multiple</strong> seats or students in the <strong>same </strong>position at the beginning.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> seats = [3,1,5], students = [2,7,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The students are moved as follows:
- The first student is moved from from position 2 to position 1 using 1 move.
- The second student is moved from from position 7 to position 5 using 2 moves.
- The third student is moved from from position 4 to position 3 using 1 move.
In total, 1 + 2 + 1 = 4 moves were used.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> seats = [4,1,5,9], students = [1,3,2,6]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The students are moved as follows:
- The first student is not moved.
- The second student is moved from from position 3 to position 4 using 1 move.
- The third student is moved from from position 2 to position 5 using 3 moves.
- The fourth student is moved from from position 6 to position 9 using 3 moves.
In total, 0 + 1 + 3 + 3 = 7 moves were used.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> seats = [2,2,6,6], students = [1,3,2,6]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Note that there are two seats at position 2 and two seats at position 6.
The students are moved as follows:
- The first student is moved from from position 1 to position 2 using 1 move.
- The second student is moved from from position 3 to position 6 using 3 moves.
- The third student is not moved.
- The fourth student is not moved.
In total, 1 + 3 + 0 + 0 = 4 moves were used.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == seats.length == students.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= seats[i], students[j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMovesToSeat(self, seats: List[int], students: List[int]) -> int:
        seats.sort()
        students.sort()
        return sum(abs(a - b) for a, b in zip(seats, students))
```

### **Java**

```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for (int i = 0; i < seats.length; ++i) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMovesToSeat(vector<int>& seats, vector<int>& students) {
        sort(seats.begin(), seats.end());
        sort(students.begin(), students.end());
        int ans = 0;
        for (int i = 0; i < seats.size(); ++i) {
            ans += abs(seats[i] - students[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func minMovesToSeat(seats []int, students []int) (ans int) {
	sort.Ints(seats)
	sort.Ints(students)
	for i, a := range seats {
		b := students[i]
		ans += abs(a - b)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minMovesToSeat(seats: number[], students: number[]): number {
    seats.sort((a, b) => a - b);
    students.sort((a, b) => a - b);
    const n = seats.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += Math.abs(seats[i] - students[i]);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_moves_to_seat(mut seats: Vec<i32>, mut students: Vec<i32>) -> i32 {
        seats.sort();
        students.sort();
        let n = seats.len();
        let mut ans = 0;
        for i in 0..n {
            ans += (seats[i] - students[i]).abs();
        }
        ans
    }
}
```

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int minMovesToSeat(int *seats, int seatsSize, int *students, int studentsSize) {
    qsort(seats, seatsSize, sizeof(int), cmp);
    qsort(students, studentsSize, sizeof(int), cmp);
    int ans = 0;
    for (int i = 0; i < seatsSize; i++) {
        ans += abs(seats[i] - students[i]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
