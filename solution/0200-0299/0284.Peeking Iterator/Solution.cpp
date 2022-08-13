/*
 * Below is the interface for Iterator, which is already defined for you.
 * **DO NOT** modify the interface for Iterator.
 *
 *  class Iterator {
 *		struct Data;
 * 		Data* data;
 *  public:
 *		Iterator(const vector<int>& nums);
 * 		Iterator(const Iterator& iter);
 *
 * 		// Returns the next element in the iteration.
 *		int next();
 *
 *		// Returns true if the iteration has more elements.
 *		bool hasNext() const;
 *	};
 */

class PeekingIterator : public Iterator {
public:
    PeekingIterator(const vector<int>& nums)
        : Iterator(nums) {
        // Initialize any member here.
        // **DO NOT** save a copy of nums and manipulate it directly.
        // You should only use the Iterator interface methods.
        hasPeeked = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    int peek() {
        if (!hasPeeked) {
            peekedElement = Iterator::next();
            hasPeeked = true;
        }
        return peekedElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    int next() {
        if (!hasPeeked) return Iterator::next();
        hasPeeked = false;
        return peekedElement;
    }

    bool hasNext() const {
        return hasPeeked || Iterator::hasNext();
    }

private:
    bool hasPeeked;
    int peekedElement;
};