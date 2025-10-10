# Product Backlog - Population Reporting System

## Project Overview
A reporting system that provides easy access to population information from an SQL database, generating various reports about countries, cities, capital cities, and population distributions.

---

## Product Backlog Items (PBIs)

### Epic 1: Country Population Reports (Priority: High)

#### PBI-1: All Countries in World by Population
**Priority:** High | **Story Points:** 3 | **Sprint:** 1
- **User Story:** As a data analyst, I want to see all countries in the world organized by population (largest to smallest) so that I can identify the most populous nations.
- **Acceptance Criteria:**
    - Query returns all countries from database
    - Sorted by population descending
    - Displays: Code, Name, Continent, Region, Population, Capital
- **Dependencies:** Database connection setup
- **Status:** To Do

#### PBI-2: Countries in Continent by Population
**Priority:** High | **Story Points:** 2 | **Sprint:** 1
- **User Story:** As a data analyst, I want to see all countries in a specific continent organized by population so that I can compare countries within a region.
- **Acceptance Criteria:**
    - User can specify continent name
    - Query returns countries filtered by continent
    - Sorted by population descending
    - Same output format as PBI-1
- **Dependencies:** PBI-1
- **Status:** To Do

#### PBI-3: Countries in Region by Population
**Priority:** High | **Story Points:** 2 | **Sprint:** 1
- **User Story:** As a data analyst, I want to see all countries in a specific region organized by population so that I can analyze sub-regional patterns.
- **Acceptance Criteria:**
    - User can specify region name
    - Query returns countries filtered by region
    - Sorted by population descending
    - Same output format as PBI-1
- **Dependencies:** PBI-1
- **Status:** To Do

#### PBI-4: Top N Countries in World
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see the top N most populated countries in the world so that I can focus on the largest nations.
- **Acceptance Criteria:**
    - User provides N as input parameter
    - Returns top N countries by population
    - Same output format as PBI-1
- **Dependencies:** PBI-1
- **Status:** To Do

#### PBI-5: Top N Countries in Continent
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see the top N most populated countries in a continent so that I can identify regional leaders.
- **Acceptance Criteria:**
    - User provides continent and N
    - Returns top N countries in that continent
- **Dependencies:** PBI-2
- **Status:** To Do

#### PBI-6: Top N Countries in Region
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see the top N most populated countries in a region so that I can analyze sub-regional leaders.
- **Acceptance Criteria:**
    - User provides region and N
    - Returns top N countries in that region
- **Dependencies:** PBI-3
- **Status:** To Do

---

### Epic 2: City Population Reports (Priority: High)

#### PBI-7: All Cities in World by Population
**Priority:** High | **Story Points:** 3 | **Sprint:** 1
- **User Story:** As a data analyst, I want to see all cities in the world organized by population so that I can identify major urban centers globally.
- **Acceptance Criteria:**
    - Query returns all cities
    - Sorted by population descending
    - Displays: Name, Country, District, Population
- **Dependencies:** Database connection setup
- **Status:** To Do

#### PBI-8: Cities in Continent by Population
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see all cities in a continent organized by population so that I can analyze urbanization by continent.
- **Acceptance Criteria:**
    - User specifies continent
    - Returns cities in that continent sorted by population
- **Dependencies:** PBI-7
- **Status:** To Do

#### PBI-9: Cities in Region by Population
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see all cities in a region organized by population.
- **Acceptance Criteria:**
    - User specifies region
    - Returns cities in that region sorted by population
- **Dependencies:** PBI-7
- **Status:** To Do

#### PBI-10: Cities in Country by Population
**Priority:** High | **Story Points:** 2 | **Sprint:** 1
- **User Story:** As a data analyst, I want to see all cities in a specific country organized by population so that I can understand national urbanization.
- **Acceptance Criteria:**
    - User specifies country
    - Returns all cities in that country
    - Sorted by population descending
- **Dependencies:** PBI-7
- **Status:** To Do

#### PBI-11: Cities in District by Population
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see all cities in a district organized by population.
- **Acceptance Criteria:**
    - User specifies district
    - Returns cities in that district
- **Dependencies:** PBI-7
- **Status:** To Do

#### PBI-12: Top N Cities in World
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see the top N most populated cities globally.
- **Acceptance Criteria:**
    - User provides N
    - Returns top N cities by population
- **Dependencies:** PBI-7
- **Status:** To Do

#### PBI-13: Top N Cities in Continent
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated cities in a continent.
- **Dependencies:** PBI-8
- **Status:** To Do

#### PBI-14: Top N Cities in Region
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated cities in a region.
- **Dependencies:** PBI-9
- **Status:** To Do

#### PBI-15: Top N Cities in Country
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see the top N most populated cities in a country.
- **Dependencies:** PBI-10
- **Status:** To Do

#### PBI-16: Top N Cities in District
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated cities in a district.
- **Dependencies:** PBI-11
- **Status:** To Do

---

### Epic 3: Capital City Reports (Priority: Medium)

#### PBI-17: All Capital Cities in World by Population
**Priority:** Medium | **Story Points:** 3 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see all capital cities organized by population so that I can compare national capitals.
- **Acceptance Criteria:**
    - Query returns all capital cities
    - Sorted by population descending
    - Displays: Name, Country, Population
- **Dependencies:** Database connection setup
- **Status:** To Do

#### PBI-18: Capital Cities in Continent by Population
**Priority:** Medium | **Story Points:** 2 | **Sprint:** 2
- **User Story:** As a data analyst, I want to see capital cities in a continent by population.
- **Dependencies:** PBI-17
- **Status:** To Do

#### PBI-19: Capital Cities in Region by Population
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see capital cities in a region by population.
- **Dependencies:** PBI-17
- **Status:** To Do

#### PBI-20: Top N Capital Cities in World
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated capital cities globally.
- **Dependencies:** PBI-17
- **Status:** To Do

#### PBI-21: Top N Capital Cities in Continent
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated capital cities in a continent.
- **Dependencies:** PBI-18
- **Status:** To Do

#### PBI-22: Top N Capital Cities in Region
**Priority:** Low | **Story Points:** 2 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see the top N most populated capital cities in a region.
- **Dependencies:** PBI-19
- **Status:** To Do

---

### Epic 4: Population Distribution Reports (Priority: Medium)

#### PBI-23: Population Distribution by Continent
**Priority:** Medium | **Story Points:** 5 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see population distribution (in cities vs not in cities) for each continent so that I can analyze urbanization rates.
- **Acceptance Criteria:**
    - For each continent: total population, population in cities (with %), population not in cities (with %)
    - Sorted by continent name
- **Dependencies:** Database connection
- **Status:** To Do

#### PBI-24: Population Distribution by Region
**Priority:** Medium | **Story Points:** 5 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see population distribution for each region.
- **Dependencies:** PBI-23
- **Status:** To Do

#### PBI-25: Population Distribution by Country
**Priority:** Medium | **Story Points:** 5 | **Sprint:** 3
- **User Story:** As a data analyst, I want to see population distribution for each country.
- **Dependencies:** PBI-23
- **Status:** To Do

---

### Epic 5: Simple Population Queries (Priority: Low)

#### PBI-26: World Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total world population.
- **Acceptance Criteria:**
    - Returns single number: total world population
- **Status:** To Do

#### PBI-27: Continent Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total population of a continent.
- **Status:** To Do

#### PBI-28: Region Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total population of a region.
- **Status:** To Do

#### PBI-29: Country Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total population of a country.
- **Status:** To Do

#### PBI-30: District Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total population of a district.
- **Status:** To Do

#### PBI-31: City Population
**Priority:** Low | **Story Points:** 1 | **Sprint:** 3
- **User Story:** As a data analyst, I want to query the total population of a city.
- **Status:** To Do

---

### Epic 6: Language Statistics (Priority: Low)

#### PBI-32: Language Speaker Statistics
**Priority:** Low | **Story Points:** 5 | **Sprint:** 4
- **User Story:** As a data analyst, I want to see the number of speakers for major languages (Chinese, English, Hindi, Spanish, Arabic) including percentage of world population.
- **Acceptance Criteria:**
    - Returns statistics for each language
    - Sorted by number of speakers (greatest to smallest)
    - Shows: Language name, number of speakers, % of world population
- **Status:** To Do

---

### Epic 7: Technical Infrastructure (Priority: Critical)

#### PBI-33: Database Connection Module
**Priority:** Critical | **Story Points:** 5 | **Sprint:** 1
- **User Story:** As a developer, I want to establish a robust database connection so that all queries can be executed reliably.
- **Acceptance Criteria:**
    - Connection class with connection pooling
    - Error handling for connection failures
    - Support for Docker environment
    - Configuration via environment variables
- **Status:** To Do

#### PBI-34: Country Model Class
**Priority:** High | **Story Points:** 2 | **Sprint:** 1
- **User Story:** As a developer, I want a Country data model so that country data is structured consistently.
- **Acceptance Criteria:**
    - Class with fields: code, name, continent, region, population, capital
    - Getters and setters
    - toString() method for display
- **Dependencies:** None
- **Status:** To Do

#### PBI-35: City Model Class
**Priority:** High | **Story Points:** 2 | **Sprint:** 1
- **User Story:** As a developer, I want a City data model so that city data is structured consistently.
- **Acceptance Criteria:**
    - Class with fields: name, country, district, population
    - Getters and setters
    - toString() method for display
- **Status:** To Do

#### PBI-36: Capital City Model Class
**Priority:** Medium | **Story Points:** 1 | **Sprint:** 2
- **User Story:** As a developer, I want a CapitalCity data model.
- **Acceptance Criteria:**
    - Class with fields: name, country, population
- **Status:** To Do

#### PBI-37: Report Formatter
**Priority:** High | **Story Points:** 3 | **Sprint:** 1
- **User Story:** As a developer, I want a consistent report formatting utility so that all outputs look professional.
- **Acceptance Criteria:**
    - Format tables with aligned columns
    - Handle null values gracefully
    - Support console and file output
- **Status:** To Do

#### PBI-38: Unit Test Framework
**Priority:** High | **Story Points:** 3 | **Sprint:** 2
- **User Story:** As a developer, I want unit tests for all query methods so that code quality is maintained.
- **Acceptance Criteria:**
    - JUnit tests for each query method
    - Mock database for testing
    - Minimum 80% code coverage
- **Status:** To Do

#### PBI-39: Integration Tests
**Priority:** Medium | **Story Points:** 5 | **Sprint:** 3
- **User Story:** As a developer, I want integration tests against the real database.
- **Status:** To Do

---

## Sprint Planning

### Sprint 1 (Weeks 4-6) - Target: 25% completion
**Goal:** Implement basic infrastructure and core country/city reports
- PBI-33: Database Connection Module (5 pts)
- PBI-34: Country Model (2 pts)
- PBI-35: City Model (2 pts)
- PBI-37: Report Formatter (3 pts)
- PBI-1: All Countries by Population (3 pts)
- PBI-2: Countries in Continent (2 pts)
- PBI-3: Countries in Region (2 pts)
- PBI-7: All Cities by Population (3 pts)
- PBI-10: Cities in Country (2 pts)
  **Total:** 24 story points

### Sprint 2 (Weeks 6-8) - Target: 50% completion
**Goal:** Complete remaining country/city queries, add capital cities, begin testing
- PBI-4, 5, 6: Top N Countries (6 pts)
- PBI-8, 9, 12, 15: Additional city queries (8 pts)
- PBI-17, 18: Capital cities basics (5 pts)
- PBI-36: Capital City Model (1 pt)
- PBI-38: Unit Test Framework (3 pts)
  **Total:** 23 story points

### Sprint 3 (Weeks 8-10) - Target: 75% completion
**Goal:** Population distributions, remaining queries, integration tests
- PBI-11, 13, 14, 16, 19-22: Remaining city/capital queries (12 pts)
- PBI-23, 24, 25: Population distributions (15 pts)
- PBI-26-31: Simple population queries (6 pts)
- PBI-39: Integration Tests (5 pts)
  **Total:** 38 story points

### Sprint 4 (Weeks 10-12) - Target: 100% completion
**Goal:** Language statistics, polish, deployment, final testing
- PBI-32: Language Statistics (5 pts)
- Bug fixes and refinements
- Documentation
- Deployment preparation
  **Total:** 5+ story points


## Backlog Summary
- **Total PBIs:** 39
- **Total Story Points:** ~100
- **Epics:** 7
- **Target for Code Review 2 (25%):** ~8 PBIs, ~24 story points