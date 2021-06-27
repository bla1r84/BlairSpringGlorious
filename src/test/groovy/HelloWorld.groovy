import com.blair.BlairSpringGlorious.model.Employee
import com.blair.BlairSpringGlorious.repositories.EmployeeRepository
import spock.lang.Shared
import spock.lang.Specification

class HelloWorld extends Specification {
    def @Shared list = [2, 3, 4]

    def setup() {
        println("setup()")
    }

    def cleanup() {
        println("cleanup()")
    }

    def setupSpec() {
        println("setupSpec()")
        list.add(9)
    }

    def 'hello world'() {
        println("hello world()")
        given:
        def i = 4

        expect:
        i % 2 == 0
        list.size() == 4
    }

    def listSize() {
        expect:
        list.size() == 4
    }

    def checkEmployees() {
        given:
        def employeeRepository = Mock(EmployeeRepository)
        def employee = new Employee()
        employee.firstName = "Ioannis"
        employee.lastName = "Lilimpakis"
        employee.salary = 5000
        employeeRepository.save(_ as Employee) >> employee

        when:
        println("New employee: ${employeeRepository.save(employee)}")

        then:
        employeeRepository.findAll().size() == 1
    }
}