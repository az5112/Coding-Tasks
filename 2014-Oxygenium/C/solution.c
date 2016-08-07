#include <stdio.h>
#include <stdlib.h>

typedef struct _VP {
	int val;
	int pos;
} VP;

typedef int cmp_ft(int, int);

int greater_equal(int a, int b)
{
	return a >= b;
}

int less_equal(int a, int b)
{
	return a <= b;
}

int add_element( VP vp, VP* arr, int begin, int end, cmp_ft cmp )
{
	while( (end >= begin) && cmp(arr[end].val, vp.val) ) {
		end--;
	}
	end++;
	arr[end] = vp;
	return end;
}

int solution( int K, int A[], int N )
{
	int result = 0;
	int i = 0, j = 0;

	VP* mini = malloc(N * sizeof(VP));
	VP* maxi = malloc(N * sizeof(VP));

	int mini_begin = 0, mini_end = -1;
	int maxi_begin = 0, maxi_end = -1;
	VP vp;

	for( i = 0; i < N; ++i ) {
		while( j < N ) {
			vp.val = A[j];
			vp.pos = j;

			mini_end = add_element( vp, mini, mini_begin, mini_end, greater_equal );
			maxi_end = add_element( vp, maxi, maxi_begin, maxi_end, less_equal );
			if( (maxi[maxi_begin].val - mini[mini_begin].val) <= K ) {
				j++;
			}
			else {
				break;
			}
		}
		result += (j -i);
		if( result > 1e9) return 1e9;

		if( mini[mini_begin].pos == i ) { mini_begin++; };
		if( maxi[maxi_begin].pos == i ) { maxi_begin++; };
	}

	return result;
}

#ifdef MY_COMPUTER
int main() {
	int A[] = {3, 5, 7, 6, 3};
	int K = 2;
	printf( "%d\n", solution( K, A, sizeof(A)/sizeof(int) ));
	return 0;
}
#endif
