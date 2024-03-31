Feature: Test REST API endpoint that uses a Lambda Token Authorizer for access control.

  Scenario Outline: Verification of endpoint using Lambda authorizationToken header with mutiple token headers
    Given user has the base uri configured
    When user send a GET request having "<authorizationToken>" header
    Then the response status code should be <statusCode>
    And  the response body should contain "<response>"

    Examples:

      |authorizationToken        |statusCode |response                                                             |

      |null                      |500       |                                                          |
      |unauthorized              |401       |Unauthorize                                                           |
      |Bearer deny               |403       |User is not authorized to access this resource with an explicit deny  |
      |RandomTestInput           |500       |                                                                  |
      |IncorrectFormattoken      |500       |                                                             |
      |expired Token             |500       |                                                             |

  Scenario Outline: Verification of endpoint using Lambda authorizationToken header with expected token header
    Given user has the base url configured
    When user send a GET request having "<authorizationToken>" header
    Then the response status code should be <statusCode>
    And  the response body should contain "<response>"

    Examples:

      |authorizationToken        |statusCode |response|

      |Bearer allow               |200       | |

  Scenario Outline: Verification of endpoint using Lambda authorizationToken header  with multiple http request type
    Given user has the base url configured
    When user send a "<Methodtype>" request having "<authorizationToken>" header
    Then the response status code should be <statusCode>
    And  the response body should contain "<response>"

    Examples:

      |Methodtype|statusCode  |response           | authorizationToken |
      |GET       |200         |                   |		  Bearer allow |
      |PUT       |403         |Forbidden          |		  Bearer allow |
      |POST      |403         |Forbidden          |		  Bearer allow |
      |PATCH     |403         |Forbidden          |		  Bearer allow |
      |DELETE    |403         |Forbidden          |		  Bearer allow |

  Scenario Outline: Verification of endpoint using Lambda authorizationToken header by hitthing incorrect endpoint
    Given user has the incorrect base url configured
    When user send a "<Methodtype>" request having "<authorizationToken>" header
    Then the response status code should be <statusCode>
    And  the response body should contain "<response>"

    Examples:

      |Methodtype|statusCode  |response                      | authorizationToken |
      |GET       |404         |             | Bearer allow       |
