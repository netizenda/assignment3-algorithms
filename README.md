# Assignment 3 Report

## Architecture Notes
- **MergeSort**
    - Depth controlled with cutoff (`n <= 16`) → switch to insertion sort.
    - Single reusable buffer used for merging instead of reallocating.
- **QuickSort**
    - Randomized pivot avoids worst-case on sorted input.
    - Always recurse into the smaller partition, iterate over the larger one → recursion depth stays `O(log n)` typical.
- **Deterministic Select (Median-of-Medians)**
    - Groups of 5 guarantee balanced splits.
    - Recurse only into the needed side, preferring the smaller one → ensures depth is limited.
- **Closest Pair of Points**
    - Divide-and-conquer approach with points pre-sorted by x.
    - Merge step maintains y-order; strip check limited by constant (7–8 neighbors).
    - Buffer reused to avoid repeated allocations.

---

## Recurrence Analysis

- **MergeSort**  
  Recurrence: `T(n) = 2T(n/2) + Θ(n)`  
  By Master Theorem (Case 2): `T(n) = Θ(n log n)`  
  Recursion depth: `O(log n)`.

- **QuickSort (randomized)**  
  Expected recurrence: `T(n) = T(k) + T(n-k-1) + Θ(n)` with random pivot giving balanced splits on average.  
  Expected runtime: `Θ(n log n)`, worst case `Θ(n²)` avoided with randomization.  
  Depth bounded to `O(log n)` by recursing into smaller side.

- **Deterministic Select**  
  Recurrence: `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)`  
  Using Akra–Bazzi intuition: `T(n) = Θ(n)`  
  Depth is bounded and scales linearly in log terms.

- **Closest Pair of Points**  
  Recurrence: `T(n) = 2T(n/2) + Θ(n)`  
  Same as MergeSort - `Θ(n log n)`  
  Recursion depth: `O(log n)`.

---

## Plots

- Expected results:
    - MergeSort and Closest Pair - scale like `n log n`.
    - QuickSort - scales like `n log n` on average.
    - Deterministic Select - scales linearly.

---

## Constant-Factor Discussion
- **Cache effects:** insertion sort cutoff improves MergeSort performance on small arrays due to cache locality.
- **Memory allocation:** reusable buffers reduce garbage collection pauses.
- **QuickSort vs MergeSort:** QuickSort often faster in practice due to in-place partitioning despite same asymptotic complexity.
- **Closest Pair:** has a higher hidden constant factor (geometry checks), but asymptotics hold.

---

## Summary
- Theoretical bounds (`Θ`-notation) aligned with measured results.
- Optimizations (cutoffs, buffer reuse, recursion strategy) significantly improved runtime.
- Randomization and median-of-medians pivoting kept recursion depth predictable.
- Minor mismatches explained by Java runtime overhead (GC) and CPU caching.  
