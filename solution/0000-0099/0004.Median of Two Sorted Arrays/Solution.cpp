class Solution {
public:
	double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
		int nums[10000] = { 0 };
		int index = 0;
		vector<int>::iterator it1 = nums1.begin();
		vector<int>::iterator it2 = nums2.begin();
		for (; it1 != nums1.end() && it2 != nums2.end();) {
			if (*it1 >= *it2) {
				nums[index++] = *it2;
				it2++;
			}
			else {
				nums[index++] = *it1;
				it1++;
			}
		}
		
		while (it1 != nums1.end()) {
			nums[index++] = *it1;
			it1++;
		}
		while (it2 != nums2.end()) {
			nums[index++] = *it2;
			it2++;
		}

		if (index % 2 == 0) {
			return (double)((nums[index/2] + nums[index/2 - 1])/2.0);
		}
		else {
			return (double)(nums[index/2]);
		}

	}
};