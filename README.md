# Percolation
This repository contains two Java APIs
  i)Percolation.java
  ii)PercolationStats.java
Percolation.java :
  This API models a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. The system is said to percolate if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

PercolationStats.java :
  This API is used to claculate the percoaltion threshold(the minimum number of sited that must be opened to make the system percolating) using Monte Carlo Simulation in which the following steps are followed :
   1)Initialize all sites to be blocked.
   2)Repeat the following until the system percolates:
      Choose a site uniformly at random among all blocked sites.
      Open the site.
   3)The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
   
