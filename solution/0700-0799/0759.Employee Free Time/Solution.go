/**
 * Definition for an Interval.
 * type Interval struct {
 *     Start int
 *     End   int
 * }
 */

func employeeFreeTime(schedule [][]*Interval) []*Interval {
	var intervals []*Interval
	for _, e := range schedule {
		intervals = append(intervals, e...)
	}

	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i].Start == intervals[j].Start {
			return intervals[i].End < intervals[j].End
		}
		return intervals[i].Start < intervals[j].Start
	})

	merged := []*Interval{intervals[0]}
	for _, cur := range intervals[1:] {
		last := merged[len(merged)-1]
		if last.End < cur.Start {
			merged = append(merged, cur)
		} else if cur.End > last.End {
			last.End = cur.End
		}
	}

	var ans []*Interval
	for i := 1; i < len(merged); i++ {
		a, b := merged[i-1], merged[i]
		ans = append(ans, &Interval{Start: a.End, End: b.Start})
	}

	return ans
}
