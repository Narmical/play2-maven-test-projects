package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends SpecificationWithJUnit {
  
  /*
   * Running tests in parallel (which would ordinarily be the default) will work only if no
   * shared resources are used (e.g. database).
   *
   * It's usually safer to run the tests sequentially.
   */
  sequential
  
  "Application" should {
    
    
    "go to login page without credentials" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val result  = route( FakeRequest( GET, "/")).get
        status(result) must equalTo(303)
      }      
    }
    "list the secured product page with credentials" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val result  = route( FakeRequest( GET, "/").withSession("email"->"guillaume@sample.com")).get
        status(result) must equalTo(200)
      }      
    }
    
  }
}
