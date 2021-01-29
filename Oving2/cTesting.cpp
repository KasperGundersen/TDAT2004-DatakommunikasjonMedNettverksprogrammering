#include <atomic>
#include <iostream>
#include <thread>
#include <vector>

using namespace std;

int main()
{
    vector<int> a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    vector<int> b = {0, 1, 2, 0, 1, 2, 0, 1, 2, 0};
    vector<int> c(10);
#pragma omp parallel for
    for (int i = 0; i < 10; i++)
    {
        c[i] = a[i] + b[i];
        cout << c[i];
    }
}
