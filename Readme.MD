#Week 4 Challenge:

## Spring Boot Robo Resume with Security
Technologies Required: Use Spring Boot. Your application must feature a Controller and multiple output pages which use Thymeleaf.

Must use a Bootstrap NavBar, Spring Security, multiple roles (employers and applicants)

Write a program that will build and display a resume and cover letter in an specific format.

###Standard NavBar
Program must contain a NavBar that contains ONLY the items listed below. Each item opens to its own web page. All data is collected in any order.

* Application logo Image (size 100x100)
* Summary
* Contact Information
* Education
* Skills
* Experience
* References (visible to the applicant only)
* View (menu with two items: Completed Resume and Cover Letter)

# Resume Features
* An image
* A name
* An email address
* Multiple educational achievements - each entered individually
* Multiple experiences - each entered individually
* Multiple skills with a rating (beginner, intermediate, proficient) 
* You must use fragments in your application 
* After entering each item the user can return to any item and edit/update the text.

Make sure that if you are using a different HTML page for each experience, skill or achievement, you can navigate between pages to add different items.

You must add validation for all entries. Decide what the validation should be for each field.

The resume and cover letter must look very professional using Bootstrap or other styling as you like.

Security Features

Two users:

* Employer
* Applicant

The applicant must log in and can view and edit and update all fields on resume and cover letter. The employer can only view resume and cover letter.

Furthermore only the applicant should see a link to their reference page. This page should have a plain text field that can be modified as the user wishes. 

Done already?

* Add validation for e-mail addresses.
* Use Gravatar for the image (figure it out)
* Use a theme of your choice to enhance how your site looks.
* Change the page background or theme based on the user
* Allow applicant to make the 'references' menu item visible to employers
* Use Lorem Ipsum for the cover letter text (figure it out), and make the font distinct from all the other fonts available on the page.
=============================================================

[IMAGE]
I am a Java Bootcamp graduate with 300+ hours of experience developing multi-user applications with Spring Data, Spring Security, JPA and Hibernate. 

John J Jingleheimer-Schmidt
jjjschmidt@gmail.com

Education
BS in Psychology, UMaryland, 2002

MS in Software Engineering,Johns Hopkins U, 2005

Experience
Software Developer II
Amtrak, June 2015 - Present
Duties: Gaudeamos Igitur Iuvenes Dum Somos

Software Developer I
Amtrak, June 2014 - June 2015
Duties: Gaudeamos Igitur Iuvenes Dum Somos

Business Analyst
DCPS, August 2005 - May 2014
Duties: Gaudeamos Igitur Iuvenes Dum Somos

Skills
PHP, highly skilled
Ruby on Rails, proficient
Perl, familiar

#Week 5 Challenge 

Build on your challenge from Week 4. 

Add a new role for recruiters. Recruiters should be able to post jobs and add desired skills to each job.

When applicants log in, they should be able to see jobs that match their skills. 

Each job should be associated with an organization, so that the candidate can search for jobs by organization. 

Done already? 

1. Add an option for an applicant to apply for a job. When the applicant applies, the recruiter will be able to see the applicant's resume. 

2. Add an option for a recruiter to shortlist candidates for a specific job.

3. An employer should be tied to an organization. When a job from that organization is posted by a recruiter, the employer should be able to see shortlisted candidates. 