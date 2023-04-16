# [1396. Design Underground System](https://leetcode.com/problems/design-underground-system)

[中文文档](/solution/1300-1399/1396.Design%20Underground%20System/README.md)

## Description

<p>An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.</p>

<p>Implement the <code>UndergroundSystem</code> class:</p>

<ul>
	<li><code>void checkIn(int id, string stationName, int t)</code>
    <ul>
    	<li>A customer with a card ID equal to <code>id</code>, checks in at the station <code>stationName</code> at time <code>t</code>.</li>
    	<li>A customer can only be checked into one place at a time.</li>
    </ul>
    </li>
    <li><code>void checkOut(int id, string stationName, int t)</code>
    <ul>
    	<li>A customer with a card ID equal to <code>id</code>, checks out from the station <code>stationName</code> at time <code>t</code>.</li>
    </ul>
    </li>
    <li><code>double getAverageTime(string startStation, string endStation)</code>
    <ul>
    	<li>Returns the average time it takes to travel from <code>startStation</code> to <code>endStation</code>.</li>
    	<li>The average time is computed from all the previous traveling times from <code>startStation</code> to <code>endStation</code> that happened <strong>directly</strong>, meaning a check in at <code>startStation</code> followed by a check out from <code>endStation</code>.</li>
    	<li>The time it takes to travel from <code>startStation</code> to <code>endStation</code> <strong>may be different</strong> from the time it takes to travel from <code>endStation</code> to <code>startStation</code>.</li>
    	<li>There will be at least one customer that has traveled from <code>startStation</code> to <code>endStation</code> before <code>getAverageTime</code> is called.</li>
    </ul>
    </li>
</ul>

<p>You may assume all calls to the <code>checkIn</code> and <code>checkOut</code> methods are consistent. If a customer checks in at time <code>t<sub>1</sub></code> then checks out at time <code>t<sub>2</sub></code>, then <code>t<sub>1</sub> &lt; t<sub>2</sub></code>. All events happen in chronological order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;UndergroundSystem&quot;,&quot;checkIn&quot;,&quot;checkIn&quot;,&quot;checkIn&quot;,&quot;checkOut&quot;,&quot;checkOut&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;,&quot;getAverageTime&quot;,&quot;checkIn&quot;,&quot;getAverageTime&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;]
[[],[45,&quot;Leyton&quot;,3],[32,&quot;Paradise&quot;,8],[27,&quot;Leyton&quot;,10],[45,&quot;Waterloo&quot;,15],[27,&quot;Waterloo&quot;,20],[32,&quot;Cambridge&quot;,22],[&quot;Paradise&quot;,&quot;Cambridge&quot;],[&quot;Leyton&quot;,&quot;Waterloo&quot;],[10,&quot;Leyton&quot;,24],[&quot;Leyton&quot;,&quot;Waterloo&quot;],[10,&quot;Waterloo&quot;,38],[&quot;Leyton&quot;,&quot;Waterloo&quot;]]

<strong>Output</strong>
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

<strong>Explanation</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, &quot;Leyton&quot;, 3);
undergroundSystem.checkIn(32, &quot;Paradise&quot;, 8);
undergroundSystem.checkIn(27, &quot;Leyton&quot;, 10);
undergroundSystem.checkOut(45, &quot;Waterloo&quot;, 15);  // Customer 45 &quot;Leyton&quot; -&gt; &quot;Waterloo&quot; in 15-3 = 12
undergroundSystem.checkOut(27, &quot;Waterloo&quot;, 20);  // Customer 27 &quot;Leyton&quot; -&gt; &quot;Waterloo&quot; in 20-10 = 10
undergroundSystem.checkOut(32, &quot;Cambridge&quot;, 22); // Customer 32 &quot;Paradise&quot; -&gt; &quot;Cambridge&quot; in 22-8 = 14
undergroundSystem.getAverageTime(&quot;Paradise&quot;, &quot;Cambridge&quot;); // return 14.00000. One trip &quot;Paradise&quot; -&gt; &quot;Cambridge&quot;, (14) / 1 = 14
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);    // return 11.00000. Two trips &quot;Leyton&quot; -&gt; &quot;Waterloo&quot;, (10 + 12) / 2 = 11
undergroundSystem.checkIn(10, &quot;Leyton&quot;, 24);
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);    // return 11.00000
undergroundSystem.checkOut(10, &quot;Waterloo&quot;, 38);  // Customer 10 &quot;Leyton&quot; -&gt; &quot;Waterloo&quot; in 38-24 = 14
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);    // return 12.00000. Three trips &quot;Leyton&quot; -&gt; &quot;Waterloo&quot;, (10 + 12 + 14) / 3 = 12
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;UndergroundSystem&quot;,&quot;checkIn&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;,&quot;checkIn&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;,&quot;checkIn&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;]
[[],[10,&quot;Leyton&quot;,3],[10,&quot;Paradise&quot;,8],[&quot;Leyton&quot;,&quot;Paradise&quot;],[5,&quot;Leyton&quot;,10],[5,&quot;Paradise&quot;,16],[&quot;Leyton&quot;,&quot;Paradise&quot;],[2,&quot;Leyton&quot;,21],[2,&quot;Paradise&quot;,30],[&quot;Leyton&quot;,&quot;Paradise&quot;]]

<strong>Output</strong>
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

<strong>Explanation</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, &quot;Leyton&quot;, 3);
undergroundSystem.checkOut(10, &quot;Paradise&quot;, 8); // Customer 10 &quot;Leyton&quot; -&gt; &quot;Paradise&quot; in 8-3 = 5
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Paradise&quot;); // return 5.00000, (5) / 1 = 5
undergroundSystem.checkIn(5, &quot;Leyton&quot;, 10);
undergroundSystem.checkOut(5, &quot;Paradise&quot;, 16); // Customer 5 &quot;Leyton&quot; -&gt; &quot;Paradise&quot; in 16-10 = 6
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Paradise&quot;); // return 5.50000, (5 + 6) / 2 = 5.5
undergroundSystem.checkIn(2, &quot;Leyton&quot;, 21);
undergroundSystem.checkOut(2, &quot;Paradise&quot;, 30); // Customer 2 &quot;Leyton&quot; -&gt; &quot;Paradise&quot; in 30-21 = 9
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Paradise&quot;); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= id, t &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= stationName.length, startStation.length, endStation.length &lt;= 10</code></li>
	<li>All strings consist of uppercase and lowercase English letters and digits.</li>
	<li>There will be at most <code>2 * 10<sup>4</sup></code> calls <strong>in total</strong> to <code>checkIn</code>, <code>checkOut</code>, and <code>getAverageTime</code>.</li>
	<li>Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class UndergroundSystem:

    def __init__(self):
        self.ts = {}
        self.d = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.ts[id] = (t, stationName)

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        t0, station = self.ts[id]
        x = self.d.get((station, stationName), (0, 0))
        self.d[(station, stationName)] = (x[0] + t - t0, x[1] + 1)

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        x = self.d[(startStation, endStation)]
        return x[0] / x[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
```

### **Java**

```java
class UndergroundSystem {
    private Map<Integer, Integer> ts = new HashMap<>();
    private Map<Integer, String> names = new HashMap<>();
    private Map<String, int[]> d = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        ts.put(id, t);
        names.put(id, stationName);
    }

    public void checkOut(int id, String stationName, int t) {
        String key = names.get(id) + "-" + stationName;
        int[] v = d.getOrDefault(key, new int[2]);
        v[0] += t - ts.get(id);
        v[1]++;
        d.put(key, v);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        int[] v = d.get(key);
        return (double) v[0] / v[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
```

### **C++**

```cpp
class UndergroundSystem {
public:
    UndergroundSystem() {
    }

    void checkIn(int id, string stationName, int t) {
        ts[id] = {stationName, t};
    }

    void checkOut(int id, string stationName, int t) {
        auto [station, t0] = ts[id];
        auto key = station + "-" + stationName;
        auto [tot, cnt] = d[key];
        d[key] = {tot + t - t0, cnt + 1};
    }

    double getAverageTime(string startStation, string endStation) {
        auto [tot, cnt] = d[startStation + "-" + endStation];
        return (double) tot / cnt;
    }

private:
    unordered_map<int, pair<string, int>> ts;
    unordered_map<string, pair<int, int>> d;
};

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem* obj = new UndergroundSystem();
 * obj->checkIn(id,stationName,t);
 * obj->checkOut(id,stationName,t);
 * double param_3 = obj->getAverageTime(startStation,endStation);
 */
```

### **Go**

```go
type UndergroundSystem struct {
	ts map[int]pair
	d  map[station][2]int
}

func Constructor() UndergroundSystem {
	return UndergroundSystem{
		ts: make(map[int]pair),
		d:  make(map[station][2]int),
	}
}

func (this *UndergroundSystem) CheckIn(id int, stationName string, t int) {
	this.ts[id] = pair{t, stationName}
}

func (this *UndergroundSystem) CheckOut(id int, stationName string, t int) {
	p := this.ts[id]
	s := station{p.a, stationName}
	if _, ok := this.d[s]; !ok {
		this.d[s] = [2]int{t - p.t, 1}
	} else {
		this.d[s] = [2]int{this.d[s][0] + t - p.t, this.d[s][1] + 1}
	}

}

func (this *UndergroundSystem) GetAverageTime(startStation string, endStation string) float64 {
	s := station{startStation, endStation}
	return float64(this.d[s][0]) / float64(this.d[s][1])
}

type station struct {
	a string
	b string
}

type pair struct {
	t int
	a string
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.CheckIn(id,stationName,t);
 * obj.CheckOut(id,stationName,t);
 * param_3 := obj.GetAverageTime(startStation,endStation);
 */
```

### **...**

```

```

<!-- tabs:end -->
