/* Median of Two Sorted Arrays
Category	Difficulty	Likes	Dislikes
algorithms	Hard (33.12%)	13569	1764
Tags
Companies
adobe | apple | dropbox | google | microsoft | yahoo | zenefits

Question - Description
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)). */


//Naive Approach 

//Solution.java

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;

		// if we merged both arrays, this would be the index containing the median (if totalLength is even, it would give the "last" median index)
		var indexAtMedian = (totalLength / 2);

		// counters to keep up with "merging" both arrays (we actually do not care with the merged array, just the value at median)
		var i = 0;
		var j = 0;

		// hold values of the two latest values at the median index (in case totalLength is even)
		var beforeMedian = 0;
		var medianIfOdd = 0;

		for (int pos = 0; pos <= indexAtMedian; pos++) {
			int valueAtPos = 0;
			if (i < nums1.length && (j >= nums2.length || nums1[i] <= nums2[j])) {
				valueAtPos = nums1[i];
				i++;
			} else {
				valueAtPos = nums2[j];
				j++;
			}

			if (pos == indexAtMedian - 1) {
				beforeMedian = valueAtPos;
			}
			if (pos == indexAtMedian) {
				medianIfOdd = valueAtPos;
			}
		}

		var isEven = totalLength % 2 == 0;

		if (isEven) {
			return ((double) beforeMedian + (double) medianIfOdd) / 2;
		} else {
			return medianIfOdd;
		}
	}
}

//Optimised Approach(Using Binary Search)

class SolutionOptimised {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);
        int n1 = nums1.length;
        int n2 = nums2.length; 
        int low = 0, high = n1;
        
        while(low <= high) {
            int cut1 = (low+high) >> 1;
            int cut2 = (n1 + n2 + 1) / 2 - cut1; 
            
        
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1]; 
            
            int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2]; 
            
            
            if(left1 <= right2 && left2 <= right1) {
                if( (n1 + n2) % 2 == 0 ) 
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0; 
                else 
                    return Math.max(left1, left2); 
            }
            else if(left1 > right2) {
                high = cut1 - 1; 
            }
            else {
                low = cut1 + 1; 
            }
        }
        return 0.0; 
    }
}


