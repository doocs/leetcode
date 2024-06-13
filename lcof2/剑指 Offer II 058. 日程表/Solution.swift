class MyCalendar {

    private var calendar: [(Int, Int)]

    init() {
        self.calendar = []
    }

    func book(_ start: Int, _ end: Int) -> Bool {
        let newEvent = (start, end)
        let index = calendar.firstIndex { $0.0 >= newEvent.1 } ?? calendar.count
        
        if index > 0 && calendar[index - 1].1 > newEvent.0 {
            return false
        }
        
        calendar.insert(newEvent, at: index)
        return true
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * let obj = MyCalendar()
 * let ret = obj.book(start, end)
 */

