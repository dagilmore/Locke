# -*- encoding: utf-8 -*-
# stub: sinatra-rabbit 1.1.6 ruby lib

Gem::Specification.new do |s|
  s.name = "sinatra-rabbit"
  s.version = "1.1.6"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["The Apache Software Foundation"]
  s.date = "2013-03-04"
  s.description = "    Rabbit is a Sinatra extension which can help you writing\n    a simple REST API using easy to undestand DSL.\n"
  s.email = "dev@deltacloud.apache.org"
  s.extra_rdoc_files = ["LICENSE"]
  s.files = ["LICENSE"]
  s.homepage = "http://github.com/mifo/sinatra-rabbit"
  s.require_paths = ["lib"]
  s.required_ruby_version = Gem::Requirement.new(">= 1.8.1")
  s.rubygems_version = "2.1.9"
  s.summary = "Sinatra REST API DSL"

  if s.respond_to? :specification_version then
    s.specification_version = 3

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<sinatra>, [">= 1.3.0"])
    else
      s.add_dependency(%q<sinatra>, [">= 1.3.0"])
    end
  else
    s.add_dependency(%q<sinatra>, [">= 1.3.0"])
  end
end
