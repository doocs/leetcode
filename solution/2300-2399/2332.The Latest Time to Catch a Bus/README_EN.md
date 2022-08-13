# [2332. The Latest Time to Catch a Bus](https://leetcode.com/problems/the-latest-time-to-catch-a-bus)

[中文文档](/solution/2300-2399/2332.The%20Latest%20Time%20to%20Catch%20a%20Bus/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>buses</code> of length <code>n</code>, where <code>buses[i]</code> represents the departure time of the <code>i<sup>th</sup></code> bus. You are also given a <strong>0-indexed</strong> integer array <code>passengers</code> of length <code>m</code>, where <code>passengers[j]</code> represents the arrival time of the <code>j<sup>th</sup></code> passenger. All bus departure times are unique. All passenger arrival times are unique.</p>

<p>You are given an integer <code>capacity</code>, which represents the <strong>maximum</strong> number of passengers that can get on each bus.</p>

<p>When a passenger arrives, they will wait in line for the next available bus. You can get on a bus that departs at <code>x</code> minutes if you arrive at <code>y</code> minutes where <code>y &lt;= x</code>, and the bus is not full. Passengers with the <strong>earliest</strong> arrival times get on the bus first.</p>

<p>More formally when a bus arrives, either:</p>

<ul>
	<li>If <code>capacity</code> or fewer passengers are waiting for a bus, they will <strong>all</strong> get on the bus, or</li>
	<li>The <code>capacity</code> passengers with the <strong>earliest</strong> arrival times will get on the bus.</li>
</ul>

<p>Return <em>the latest time you may arrive at the bus station to catch a bus</em>. You <strong>cannot</strong> arrive at the same time as another passenger.</p>

<p><strong>Note: </strong>The arrays <code>buses</code> and <code>passengers</code> are not necessarily sorted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> buses = [10,20], passengers = [2,17,18,19], capacity = 2
<strong>Output:</strong> 16
<strong>Explanation:</strong> Suppose you arrive at time 16.
At time 10, the first bus departs with the 0<sup>th</sup> passenger. 
At time 20, the second bus departs with you and the 1<sup>st</sup> passenger.
Note that you may not arrive at the same time as another passenger, which is why you must arrive before the 1<sup>st</sup> passenger to catch the bus.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> Suppose you arrive at time 20.
At time 10, the first bus departs with the 3<sup>rd</sup> passenger. 
At time 20, the second bus departs with the 5<sup>th</sup> and 1<sup>st</sup> passengers.
At time 30, the third bus departs with the 0<sup>th</sup> passenger and you.
Notice if you had arrived any later, then the 6<sup>th</sup> passenger would have taken your seat on the third bus.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == buses.length</code></li>
	<li><code>m == passengers.length</code></li>
	<li><code>1 &lt;= n, m, capacity &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= buses[i], passengers[i] &lt;= 10<sup>9</sup></code></li>
	<li>Each element in <code>buses</code> is <strong>unique</strong>.</li>
	<li>Each element in <code>passengers</code> is <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def latestTimeCatchTheBus(
        self, buses: List[int], passengers: List[int], capacity: int
    ) -> int:
        buses.sort()
        passengers.sort()
        j = 0
        for t in buses:
            c = capacity
            while c and j < len(passengers) and passengers[j] <= t:
                c, j = c - 1, j + 1
        j -= 1
        ans = buses[-1] if c else passengers[j]
        while ~j and passengers[j] == ans:
            ans, j = ans - 1, j - 1
        return ans
```

### **Java**

```java
class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int j = 0, c = 0;
        for (int t : buses) {
            c = capacity;
            while (c > 0 && j < passengers.length && passengers[j] <= t) {
                --c;
                ++j;
            }
        }
        --j;
        int ans = c > 0 ? buses[buses.length - 1] : passengers[j];
        while (j >= 0 && ans == passengers[j]) {
            --ans;
            --j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int latestTimeCatchTheBus(vector<int>& buses, vector<int>& passengers, int capacity) {
        sort(buses.begin(), buses.end());
        sort(passengers.begin(), passengers.end());
        int j = 0, c = 0;
        for (int t : buses) {
            c = capacity;
            while (c && j < passengers.size() && passengers[j] <= t) --c, ++j;
        }
        --j;
        int ans = c ? buses[buses.size() - 1] : passengers[j];
        while (~j && ans == passengers[j]) --j, --ans;
        return ans;
    }
};
```

### **Go**

```go
func latestTimeCatchTheBus(buses []int, passengers []int, capacity int) int {
	sort.Ints(buses)
	sort.Ints(passengers)
	j, c := 0, 0
	for _, t := range buses {
		c = capacity
		for c > 0 && j < len(passengers) && passengers[j] <= t {
			j++
			c--
		}
	}
	j--
	ans := buses[len(buses)-1]
	if c == 0 {
		ans = passengers[j]
	}
	for j >= 0 && ans == passengers[j] {
		ans--
		j--
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
