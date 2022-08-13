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
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

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
        self.check_in_station = {}
        self.check_in_time = {}
        self.total_time = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.check_in_station[id] = stationName
        self.check_in_time[id] = t

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        cost = t - self.check_in_time.pop(id)
        start_station = self.check_in_station.pop(id)
        stations = start_station + '.' + stationName
        times = self.total_time.get(stations, [0, 0])
        times[0] += cost
        times[1] += 1
        self.total_time[stations] = times

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        stations = startStation + '.' + endStation
        times = self.total_time[stations]
        return times[0] / times[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
```

### **Java**

```java
class UndergroundSystem {
    private Map<Integer, String> checkInStation;
    private Map<Integer, Integer> checkInTime;
    private Map<String, int[]> totalTime;

    public UndergroundSystem() {
        checkInStation = new HashMap<>();
        checkInTime = new HashMap<>();
        totalTime = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInStation.put(id, stationName);
        checkInTime.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        int cost = t - checkInTime.remove(id);
        String startStation = checkInStation.remove(id);
        String stations = startStation + "." + stationName;
        int[] times = totalTime.getOrDefault(stations, new int[2]);
        times[0] += cost;
        ++times[1];
        totalTime.put(stations, times);
    }

    public double getAverageTime(String startStation, String endStation) {
        String stations = startStation + "." + endStation;
        int[] times = totalTime.get(stations);
        return times[0] * 1.0 / times[1];
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

### **...**

```

```

<!-- tabs:end -->
