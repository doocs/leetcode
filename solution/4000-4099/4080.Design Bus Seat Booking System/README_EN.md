---
comments: true
difficulty: Medium
rating: 1778
source: Weekly Contest 523 Q3
---

<!-- problem:start -->

# [4080. Design Bus Seat Booking System](https://leetcode.com/problems/design-bus-seat-booking-system)

[中文文档](/solution/4000-4099/4080.Design%20Bus%20Seat%20Booking%20System/README.md)

## Description

<!-- description:start -->

<p>You are asked to design a seat booking system for a bus.</p>

<p>The bus has <code>n</code> rows. Each row has four seats labeled <code>&quot;A&quot;</code>, <code>&quot;B&quot;</code>, <code>&quot;C&quot;</code>, and <code>&quot;D&quot;</code>.</p>

<p>Seats <code>&quot;A&quot;</code> and <code>&quot;D&quot;</code> are <strong>window</strong> seats; seats <code>&quot;B&quot;</code> and <code>&quot;C&quot;</code> are <strong>middle</strong> seats. In each row, seat <code>&quot;A&quot;</code> is <strong>adjacent</strong> to seat <code>&quot;B&quot;</code>, and seat <code>&quot;D&quot;</code> is <strong>adjacent</strong> to seat <code>&quot;C&quot;</code>.</p>

<p>Booking rules:</p>

<ul>
	<li>Booking a <strong>middle</strong> seat (<code>&quot;B&quot;</code> or <code>&quot;C&quot;</code>) always takes 1 second.</li>
	<li>Booking a <strong>window</strong> seat (<code>&quot;A&quot;</code> or <code>&quot;D&quot;</code>) takes 1 second if its <strong>adjacent middle</strong> seat is <strong>not</strong> booked, or 3 seconds (an extra delay of 2 seconds) if its <strong>adjacent middle</strong> seat is <strong>already</strong> booked.</li>
</ul>

<p>A seat is identified by a string such as <code>&quot;A1&quot;</code> or <code>&quot;D20&quot;</code> (one letter from <code>A</code> to <code>D</code> followed by its row number).</p>

<p>Implement the <code>BusBooking</code> class:</p>

<ul>
	<li><code>BusBooking(int n)</code>: Initializes the system with <code>n</code> <strong>empty</strong> rows.</li>
	<li><code>void toggle(string seat)</code>: Books <code>seat</code> if it is free, or frees it if it is already booked.</li>
	<li><code>int getTotalTime()</code>: Returns the total time (in seconds) taken to book all <strong>currently</strong> booked seats, where the seats are booked in the order their <code>toggle</code> calls were made. If no seats are currently booked, return 0.</li>
	<li><code>int getMinTime()</code>: Returns the <strong>smallest</strong> possible total time (in seconds) to book all <strong>currently</strong> booked seats, over every possible order of booking them. If no seats are currently booked, return 0.</li>
</ul>

<p><strong>Note</strong>: The time cost of a seat is fixed at the moment it is booked, based on the seats booked at that time. Booking or freeing other seats afterward does not change the cost of a seat that is already booked.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;BusBooking&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;]<br />
[[20], [&quot;B3&quot;], [&quot;A3&quot;], [], [], [&quot;C3&quot;], [&quot;D3&quot;], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 4, 2, null, null, 8, 4] </span></p>

<p><strong>Explanation</strong></p>
BusBooking busBooking = new BusBooking(20); // start with 20 empty rows<br />
busBooking.toggle(&quot;B3&quot;); // book <code>&quot;B3&quot;</code>, a middle seat, which costs 1 second. Total time is now 1.<br />
busBooking.toggle(&quot;A3&quot;); // book <code>&quot;A3&quot;</code>, a window seat. Its adjacent middle <code>&quot;B3&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>1 + 3 = 4</code>.<br />
busBooking.getTotalTime(); // return 4, since in toggle order <code>&quot;B3&quot;</code> costs 1 and <code>&quot;A3&quot;</code> costs 3.<br />
busBooking.getMinTime(); // return 2. Booking <code>&quot;A3&quot;</code> before <code>&quot;B3&quot;</code> avoids the delay, so 2 seats takes 2 second to book.<br />
busBooking.toggle(&quot;C3&quot;); // book <code>&quot;C3&quot;</code>, a middle seat, which costs 1 second. Total time is now <code>1 + 4 = 5</code>.<br />
busBooking.toggle(&quot;D3&quot;); // book <code>&quot;D3&quot;</code>, a window seat. Its adjacent middle <code>&quot;C3&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>5 + 3 = 8</code>.<br />
busBooking.getTotalTime(); // return 8, since in toggle order the seat costs are 1, 3, 1, 3.<br />
busBooking.getMinTime(); // return 4. With the best possible booking order, no delay is added, so the 4 booked seats take <code>4 * 1 = 4</code> seconds.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;BusBooking&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;toggle&quot;, &quot;getTotalTime&quot;, &quot;getMinTime&quot;]<br />
[[10], [&quot;C7&quot;], [&quot;D7&quot;], [], [&quot;D7&quot;], [&quot;C7&quot;], [&quot;D7&quot;], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 4, null, null, null, 1, 1] </span></p>

<p><strong>Explanation</strong></p>
BusBooking busBooking = new BusBooking(10); // start with 10 empty rows<br />
busBooking.toggle(&quot;C7&quot;); // book <code>&quot;C7&quot;</code>, a middle seat, which costs 1 second. Total time is now 1.<br />
busBooking.toggle(&quot;D7&quot;); // book <code>&quot;D7&quot;</code>, a window seat. Its adjacent middle <code>&quot;C7&quot;</code> is already booked, so it costs 3 seconds. Total time is now <code>1 + 3 = 4</code>.<br />
busBooking.getTotalTime(); // return 4, since in toggle order <code>&quot;C7&quot;</code> costs 1 and <code>&quot;D7&quot;</code> costs 3.<br />
busBooking.toggle(&quot;D7&quot;); // <code>&quot;D7&quot;</code> is already booked, so free it. This removes its cost of 3. Total time is now <code>4 - 3 = 1</code>.<br />
busBooking.toggle(&quot;C7&quot;); // <code>&quot;C7&quot;</code> is already booked, so free it. This removes its cost of 1. Total time is now <code>1 - 1 = 0</code>, and row 7 is empty.<br />
busBooking.toggle(&quot;D7&quot;); // book <code>&quot;D7&quot;</code> again, a window seat. Its adjacent middle <code>&quot;C7&quot;</code> is not booked this time, so it costs only 1 second. Total time is now <code>0 + 1 = 1</code>.<br />
busBooking.getTotalTime(); // return 1, since only <code>&quot;D7&quot;</code> is booked and it cost 1 this time.<br />
busBooking.getMinTime(); // return 1. With the best possible booking order, the 1 booked seat takes <code>1 * 1 = 1</code> second.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>seat[0]</code> is one of <code>&#39;A&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;D&#39;</code>.</li>
	<li>The remaining characters of <code>seat</code> form an integer <code>row</code> with <code>1 &lt;= row &lt;= n</code>.</li>
	<li>The total number of calls to <code>toggle</code>, <code>getTotalTime</code>, and <code>getMinTime</code> does not exceed <code>10<sup>5</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Record the Booking Cost

<!-- thinking:start -->

> **Thinking**
>
> Both the number of rows and the number of calls can reach $10^5$, so replaying the whole booking history on every query exceeds the limit.
>
> A window seat costs an extra $2$ seconds only when its adjacent middle seat is already booked, and that cost is fixed at the moment $\textit{toggle}$ books it. $\textit{getTotalTime}$ is the sum of the costs recorded for the seats that are still occupied.
>
> $\textit{getMinTime}$ may reorder the booking of those seats. A middle seat always costs $1$ second; booking a window seat before its adjacent middle seat avoids the extra $2$ seconds. The minimum time is therefore the number of seats currently booked. Store each seat's cost from the moment it was booked, keep the total and the occupancy count separately, and every operation is $O(1)$.

<!-- thinking:end -->

Each row is stored as an array of length $4$. A cell value of $0$ means the seat is free; otherwise it is the number of seconds fixed when that seat was booked. The variable $\textit{tot}$ keeps the sum of these costs, and a counter keeps the number of seats currently booked.

When $\textit{toggle}$ sees a booked seat, subtract its stored cost from $\textit{tot}$, decrease the counter, and clear the cell. When the seat is free, a middle seat $\texttt{B}$ or $\texttt{C}$ costs $1$ second. A window seat $\texttt{A}$ or $\texttt{D}$ costs $3$ seconds if its adjacent middle seat is already booked, and $1$ second otherwise. Write that cost into the array and update both $\textit{tot}$ and the counter.

$\textit{getTotalTime}$ returns $\textit{tot}$, and $\textit{getMinTime}$ returns the number of booked seats.

Each operation takes $O(1)$ time, and the space complexity is $O(n)$, where $n$ is the number of rows.

<!-- tabs:start -->

#### Python3

```python
class BusBooking:
    def __init__(self, n: int):
        self.tot = 0
        self.min_tot = 0
        self.cost_data = [[0] * 4 for _ in range(n)]

    def toggle(self, seat: str) -> None:
        i = int(seat[1:]) - 1
        j = ord(seat[0]) - ord("A")
        if self.cost_data[i][j] > 0:
            self.min_tot -= 1
            self.tot -= self.cost_data[i][j]
            self.cost_data[i][j] = 0
            return
        cost = 1
        if (j == 0 and self.cost_data[i][1] > 0) or (
            j == 3 and self.cost_data[i][2] > 0
        ):
            cost = 3
        self.min_tot += 1
        self.tot += cost
        self.cost_data[i][j] = cost

    def getTotalTime(self) -> int:
        return self.tot

    def getMinTime(self) -> int:
        return self.min_tot


# Your BusBooking object will be instantiated and called as such:
# obj = BusBooking(n)
# obj.toggle(seat)
# param_2 = obj.getTotalTime()
# param_3 = obj.getMinTime()
```

#### Java

```java
class BusBooking {
    private int tot;
    private int minTot;
    private int[][] costData;

    public BusBooking(int n) {
        this.tot = 0;
        this.minTot = 0;
        this.costData = new int[n][4];
    }

    public void toggle(String seat) {
        int i = Integer.parseInt(seat.substring(1)) - 1;
        int j = seat.charAt(0) - 'A';
        if (costData[i][j] > 0) {
            minTot -= 1;
            tot -= costData[i][j];
            costData[i][j] = 0;
            return;
        }
        int cost = 1;
        if ((j == 0 && costData[i][1] > 0) || (j == 3 && costData[i][2] > 0)) {
            cost = 3;
        }
        minTot += 1;
        tot += cost;
        costData[i][j] = cost;
    }

    public int getTotalTime() {
        return tot;
    }

    public int getMinTime() {
        return minTot;
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking obj = new BusBooking(n);
 * obj.toggle(seat);
 * int param_2 = obj.getTotalTime();
 * int param_3 = obj.getMinTime();
 */
```

#### C++

```cpp
class BusBooking {
private:
    int tot;
    int min_tot;
    vector<array<int, 4>> cost_data;

public:
    BusBooking(int n) {
        tot = 0;
        min_tot = 0;
        cost_data = vector<array<int, 4>>(n, {0, 0, 0, 0});
    }

    void toggle(string seat) {
        int i = 0;
        for (int k = 1; k < seat.size(); k++) {
            i = i * 10 + (seat[k] - '0');
        }
        i -= 1;
        int j = seat[0] - 'A';

        if (cost_data[i][j] > 0) {
            min_tot -= 1;
            tot -= cost_data[i][j];
            cost_data[i][j] = 0;
            return;
        }

        int cost = 1;
        if ((j == 0 && cost_data[i][1] > 0) || (j == 3 && cost_data[i][2] > 0)) {
            cost = 3;
        }
        min_tot += 1;
        tot += cost;
        cost_data[i][j] = cost;
    }

    int getTotalTime() {
        return tot;
    }

    int getMinTime() {
        return min_tot;
    }
};

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking* obj = new BusBooking(n);
 * obj->toggle(seat);
 * int param_2 = obj->getTotalTime();
 * int param_3 = obj->getMinTime();
 */
```

#### Go

```go
type BusBooking struct {
	tot      int
	minTot   int
	costData [][]int
}

func Constructor(n int) BusBooking {
	costData := make([][]int, n)
	for i := range costData {
		costData[i] = make([]int, 4)
	}
	return BusBooking{
		tot:      0,
		minTot:   0,
		costData: costData,
	}
}

func (this *BusBooking) Toggle(seat string) {
	i := 0
	for k := 1; k < len(seat); k++ {
		i = i*10 + int(seat[k]-'0')
	}
	i -= 1
	j := int(seat[0] - 'A')

	if this.costData[i][j] > 0 {
		this.minTot -= 1
		this.tot -= this.costData[i][j]
		this.costData[i][j] = 0
		return
	}

	cost := 1
	if (j == 0 && this.costData[i][1] > 0) || (j == 3 && this.costData[i][2] > 0) {
		cost = 3
	}
	this.minTot += 1
	this.tot += cost
	this.costData[i][j] = cost
}

func (this *BusBooking) GetTotalTime() int {
	return this.tot
}

func (this *BusBooking) GetMinTime() int {
	return this.minTot
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * obj := Constructor(n);
 * obj.Toggle(seat);
 * param_2 := obj.GetTotalTime();
 * param_3 := obj.GetMinTime();
 */
```

#### TypeScript

```ts
class BusBooking {
    private tot: number;
    private minTot: number;
    private costData: number[][];

    constructor(n: number) {
        this.tot = 0;
        this.minTot = 0;
        this.costData = Array.from({ length: n }, () => new Array<number>(4).fill(0));
    }

    toggle(seat: string): void {
        let i = 0;
        for (let k = 1; k < seat.length; k++) {
            i = i * 10 + (seat.charCodeAt(k) - '0'.charCodeAt(0));
        }
        i -= 1;
        const j = seat.charCodeAt(0) - 'A'.charCodeAt(0);

        if (this.costData[i][j] > 0) {
            this.minTot -= 1;
            this.tot -= this.costData[i][j];
            this.costData[i][j] = 0;
            return;
        }

        let cost = 1;
        if ((j === 0 && this.costData[i][1] > 0) || (j === 3 && this.costData[i][2] > 0)) {
            cost = 3;
        }
        this.minTot += 1;
        this.tot += cost;
        this.costData[i][j] = cost;
    }

    getTotalTime(): number {
        return this.tot;
    }

    getMinTime(): number {
        return this.minTot;
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * var obj = new BusBooking(n)
 * obj.toggle(seat)
 * var param_2 = obj.getTotalTime()
 * var param_3 = obj.getMinTime()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
